import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { TbDatafileentMySuffix } from './tb-datafileent-my-suffix.model';
import { TbDatafileentMySuffixService } from './tb-datafileent-my-suffix.service';

@Injectable()
export class TbDatafileentMySuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private tbDatafileentService: TbDatafileentMySuffixService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.tbDatafileentService.find(id)
                    .subscribe((tbDatafileentResponse: HttpResponse<TbDatafileentMySuffix>) => {
                        const tbDatafileent: TbDatafileentMySuffix = tbDatafileentResponse.body;
                        this.ngbModalRef = this.tbDatafileentModalRef(component, tbDatafileent);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.tbDatafileentModalRef(component, new TbDatafileentMySuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    tbDatafileentModalRef(component: Component, tbDatafileent: TbDatafileentMySuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.tbDatafileent = tbDatafileent;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
