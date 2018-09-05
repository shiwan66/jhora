import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TBdatafileMySuffix } from './t-bdatafile-my-suffix.model';
import { TBdatafileMySuffixPopupService } from './t-bdatafile-my-suffix-popup.service';
import { TBdatafileMySuffixService } from './t-bdatafile-my-suffix.service';

@Component({
    selector: 'jhi-t-bdatafile-my-suffix-dialog',
    templateUrl: './t-bdatafile-my-suffix-dialog.component.html'
})
export class TBdatafileMySuffixDialogComponent implements OnInit {

    tBdatafile: TBdatafileMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private tBdatafileService: TBdatafileMySuffixService,
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
        if (this.tBdatafile.id !== undefined) {
            this.subscribeToSaveResponse(
                this.tBdatafileService.update(this.tBdatafile));
        } else {
            this.subscribeToSaveResponse(
                this.tBdatafileService.create(this.tBdatafile));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<TBdatafileMySuffix>>) {
        result.subscribe((res: HttpResponse<TBdatafileMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: TBdatafileMySuffix) {
        this.eventManager.broadcast({ name: 'tBdatafileListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-t-bdatafile-my-suffix-popup',
    template: ''
})
export class TBdatafileMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tBdatafilePopupService: TBdatafileMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.tBdatafilePopupService
                    .open(TBdatafileMySuffixDialogComponent as Component, params['id']);
            } else {
                this.tBdatafilePopupService
                    .open(TBdatafileMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
