import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TbDatafileentMySuffix } from './tb-datafileent-my-suffix.model';
import { TbDatafileentMySuffixPopupService } from './tb-datafileent-my-suffix-popup.service';
import { TbDatafileentMySuffixService } from './tb-datafileent-my-suffix.service';

@Component({
    selector: 'jhi-tb-datafileent-my-suffix-dialog',
    templateUrl: './tb-datafileent-my-suffix-dialog.component.html'
})
export class TbDatafileentMySuffixDialogComponent implements OnInit {

    tbDatafileent: TbDatafileentMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private tbDatafileentService: TbDatafileentMySuffixService,
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
        if (this.tbDatafileent.id !== undefined) {
            this.subscribeToSaveResponse(
                this.tbDatafileentService.update(this.tbDatafileent));
        } else {
            this.subscribeToSaveResponse(
                this.tbDatafileentService.create(this.tbDatafileent));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<TbDatafileentMySuffix>>) {
        result.subscribe((res: HttpResponse<TbDatafileentMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: TbDatafileentMySuffix) {
        this.eventManager.broadcast({ name: 'tbDatafileentListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-tb-datafileent-my-suffix-popup',
    template: ''
})
export class TbDatafileentMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tbDatafileentPopupService: TbDatafileentMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.tbDatafileentPopupService
                    .open(TbDatafileentMySuffixDialogComponent as Component, params['id']);
            } else {
                this.tbDatafileentPopupService
                    .open(TbDatafileentMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
