import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TbDMySuffix } from './tb-d-my-suffix.model';
import { TbDMySuffixPopupService } from './tb-d-my-suffix-popup.service';
import { TbDMySuffixService } from './tb-d-my-suffix.service';

@Component({
    selector: 'jhi-tb-d-my-suffix-dialog',
    templateUrl: './tb-d-my-suffix-dialog.component.html'
})
export class TbDMySuffixDialogComponent implements OnInit {

    tbD: TbDMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private tbDService: TbDMySuffixService,
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
        if (this.tbD.id !== undefined) {
            this.subscribeToSaveResponse(
                this.tbDService.update(this.tbD));
        } else {
            this.subscribeToSaveResponse(
                this.tbDService.create(this.tbD));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<TbDMySuffix>>) {
        result.subscribe((res: HttpResponse<TbDMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: TbDMySuffix) {
        this.eventManager.broadcast({ name: 'tbDListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-tb-d-my-suffix-popup',
    template: ''
})
export class TbDMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tbDPopupService: TbDMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.tbDPopupService
                    .open(TbDMySuffixDialogComponent as Component, params['id']);
            } else {
                this.tbDPopupService
                    .open(TbDMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
