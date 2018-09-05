import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { MxpmsSearchEquipmentMySuffix } from './mxpms-search-equipment-my-suffix.model';
import { MxpmsSearchEquipmentMySuffixPopupService } from './mxpms-search-equipment-my-suffix-popup.service';
import { MxpmsSearchEquipmentMySuffixService } from './mxpms-search-equipment-my-suffix.service';

@Component({
    selector: 'jhi-mxpms-search-equipment-my-suffix-dialog',
    templateUrl: './mxpms-search-equipment-my-suffix-dialog.component.html'
})
export class MxpmsSearchEquipmentMySuffixDialogComponent implements OnInit {

    mxpmsSearchEquipment: MxpmsSearchEquipmentMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private mxpmsSearchEquipmentService: MxpmsSearchEquipmentMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.mxpmsSearchEquipment.id !== undefined) {
            this.subscribeToSaveResponse(
                this.mxpmsSearchEquipmentService.update(this.mxpmsSearchEquipment));
        } else {
            this.subscribeToSaveResponse(
                this.mxpmsSearchEquipmentService.create(this.mxpmsSearchEquipment));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<MxpmsSearchEquipmentMySuffix>>) {
        result.subscribe((res: HttpResponse<MxpmsSearchEquipmentMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: MxpmsSearchEquipmentMySuffix) {
        this.eventManager.broadcast({ name: 'mxpmsSearchEquipmentListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-mxpms-search-equipment-my-suffix-popup',
    template: ''
})
export class MxpmsSearchEquipmentMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private mxpmsSearchEquipmentPopupService: MxpmsSearchEquipmentMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.mxpmsSearchEquipmentPopupService
                    .open(MxpmsSearchEquipmentMySuffixDialogComponent as Component, params['id']);
            } else {
                this.mxpmsSearchEquipmentPopupService
                    .open(MxpmsSearchEquipmentMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
