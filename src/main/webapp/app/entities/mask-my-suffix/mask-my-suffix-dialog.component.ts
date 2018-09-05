import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { MaskMySuffix } from './mask-my-suffix.model';
import { MaskMySuffixPopupService } from './mask-my-suffix-popup.service';
import { MaskMySuffixService } from './mask-my-suffix.service';

@Component({
    selector: 'jhi-mask-my-suffix-dialog',
    templateUrl: './mask-my-suffix-dialog.component.html'
})
export class MaskMySuffixDialogComponent implements OnInit {

    mask: MaskMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private maskService: MaskMySuffixService,
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
        if (this.mask.id !== undefined) {
            this.subscribeToSaveResponse(
                this.maskService.update(this.mask));
        } else {
            this.subscribeToSaveResponse(
                this.maskService.create(this.mask));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<MaskMySuffix>>) {
        result.subscribe((res: HttpResponse<MaskMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: MaskMySuffix) {
        this.eventManager.broadcast({ name: 'maskListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-mask-my-suffix-popup',
    template: ''
})
export class MaskMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private maskPopupService: MaskMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.maskPopupService
                    .open(MaskMySuffixDialogComponent as Component, params['id']);
            } else {
                this.maskPopupService
                    .open(MaskMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
