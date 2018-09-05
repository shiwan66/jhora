import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { MxpmsSearchEquipmentMySuffix } from './mxpms-search-equipment-my-suffix.model';
import { MxpmsSearchEquipmentMySuffixService } from './mxpms-search-equipment-my-suffix.service';

@Injectable()
export class MxpmsSearchEquipmentMySuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private mxpmsSearchEquipmentService: MxpmsSearchEquipmentMySuffixService

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
                this.mxpmsSearchEquipmentService.find(id)
                    .subscribe((mxpmsSearchEquipmentResponse: HttpResponse<MxpmsSearchEquipmentMySuffix>) => {
                        const mxpmsSearchEquipment: MxpmsSearchEquipmentMySuffix = mxpmsSearchEquipmentResponse.body;
                        this.ngbModalRef = this.mxpmsSearchEquipmentModalRef(component, mxpmsSearchEquipment);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.mxpmsSearchEquipmentModalRef(component, new MxpmsSearchEquipmentMySuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    mxpmsSearchEquipmentModalRef(component: Component, mxpmsSearchEquipment: MxpmsSearchEquipmentMySuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.mxpmsSearchEquipment = mxpmsSearchEquipment;
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
