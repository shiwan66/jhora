import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { TBdatafileMySuffix } from './t-bdatafile-my-suffix.model';
import { TBdatafileMySuffixService } from './t-bdatafile-my-suffix.service';

@Injectable()
export class TBdatafileMySuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private tBdatafileService: TBdatafileMySuffixService

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
                this.tBdatafileService.find(id)
                    .subscribe((tBdatafileResponse: HttpResponse<TBdatafileMySuffix>) => {
                        const tBdatafile: TBdatafileMySuffix = tBdatafileResponse.body;
                        this.ngbModalRef = this.tBdatafileModalRef(component, tBdatafile);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.tBdatafileModalRef(component, new TBdatafileMySuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    tBdatafileModalRef(component: Component, tBdatafile: TBdatafileMySuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.tBdatafile = tBdatafile;
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
