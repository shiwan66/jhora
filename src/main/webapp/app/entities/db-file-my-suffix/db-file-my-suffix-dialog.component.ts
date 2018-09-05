import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { DBFileMySuffix } from './db-file-my-suffix.model';
import { DBFileMySuffixPopupService } from './db-file-my-suffix-popup.service';
import { DBFileMySuffixService } from './db-file-my-suffix.service';

@Component({
    selector: 'jhi-db-file-my-suffix-dialog',
    templateUrl: './db-file-my-suffix-dialog.component.html'
})
export class DBFileMySuffixDialogComponent implements OnInit {

    dBFile: DBFileMySuffix;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private dBFileService: DBFileMySuffixService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.dBFile.id !== undefined) {
            this.subscribeToSaveResponse(
                this.dBFileService.update(this.dBFile));
        } else {
            this.subscribeToSaveResponse(
                this.dBFileService.create(this.dBFile));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<DBFileMySuffix>>) {
        result.subscribe((res: HttpResponse<DBFileMySuffix>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: DBFileMySuffix) {
        this.eventManager.broadcast({ name: 'dBFileListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-db-file-my-suffix-popup',
    template: ''
})
export class DBFileMySuffixPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private dBFilePopupService: DBFileMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.dBFilePopupService
                    .open(DBFileMySuffixDialogComponent as Component, params['id']);
            } else {
                this.dBFilePopupService
                    .open(DBFileMySuffixDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
