import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TbEMySuffix } from './tb-e-my-suffix.model';
import { TbEMySuffixPopupService } from './tb-e-my-suffix-popup.service';
import { TbEMySuffixService } from './tb-e-my-suffix.service';

@Component({
    selector: 'jhi-tb-e-my-suffix-dialog',
    templateUrl: './tb-e-my-suffix-dialog.component.html'
})
export class TbEMySuffixDialogComponent implements OnInit {

    tbE: TbEMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private tbEService: TbEMySuffixService,
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
        if (this.tbE.id !== undefined) {
            this.subscribeToSaveResponse(
                this.tbEService.update(this.tbE));
        } else {
            this.subscribeToSaveResponse(
                this.tbEService.create(this.tbE));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<TbEMySuffix>>) {
        result.subscribe((res: HttpResponse<TbEMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: TbEMySuffix) {
        this.eventManager.broadcast({ name: 'tbEListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-tb-e-my-suffix-popup',
    template: ''
})
export class TbEMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tbEPopupService: TbEMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.tbEPopupService
                    .open(TbEMySuffixDialogComponent as Component, params['id']);
            } else {
                this.tbEPopupService
                    .open(TbEMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
