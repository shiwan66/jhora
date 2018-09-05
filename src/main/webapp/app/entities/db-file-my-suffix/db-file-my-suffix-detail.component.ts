import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { DBFileMySuffix } from './db-file-my-suffix.model';
import { DBFileMySuffixService } from './db-file-my-suffix.service';

@Component({
    selector: 'jhi-db-file-my-suffix-detail',
    templateUrl: './db-file-my-suffix-detail.component.html'
})
export class DBFileMySuffixDetailComponent implements OnInit, OnDestroy {

    dBFile: DBFileMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private dBFileService: DBFileMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDBFiles();
    }

    load(id) {
        this.dBFileService.find(id)
            .subscribe((dBFileResponse: HttpResponse<DBFileMySuffix>) => {
                this.dBFile = dBFileResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDBFiles() {
        this.eventSubscriber = this.eventManager.subscribe(
            'dBFileListModification',
            (response) => this.load(this.dBFile.id)
        );
    }
}
