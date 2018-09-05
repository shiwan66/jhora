import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { DBFileMySuffix } from './db-file-my-suffix.model';
import { DBFileMySuffixPopupService } from './db-file-my-suffix-popup.service';
import { DBFileMySuffixService } from './db-file-my-suffix.service';

@Component({
    selector: 'jhi-db-file-my-suffix-delete-dialog',
    templateUrl: './db-file-my-suffix-delete-dialog.component.html'
})
export class DBFileMySuffixDeleteDialogComponent {

    dBFile: DBFileMySuffix;

    constructor(
        private dBFileService: DBFileMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.dBFileService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'dBFileListModification',
                content: 'Deleted an dBFile'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-db-file-my-suffix-delete-popup',
    template: ''
})
export class DBFileMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private dBFilePopupService: DBFileMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.dBFilePopupService
                .open(DBFileMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
