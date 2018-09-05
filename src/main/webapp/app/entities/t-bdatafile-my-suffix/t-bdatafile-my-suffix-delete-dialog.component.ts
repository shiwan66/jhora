import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TBdatafileMySuffix } from './t-bdatafile-my-suffix.model';
import { TBdatafileMySuffixPopupService } from './t-bdatafile-my-suffix-popup.service';
import { TBdatafileMySuffixService } from './t-bdatafile-my-suffix.service';

@Component({
    selector: 'jhi-t-bdatafile-my-suffix-delete-dialog',
    templateUrl: './t-bdatafile-my-suffix-delete-dialog.component.html'
})
export class TBdatafileMySuffixDeleteDialogComponent {

    tBdatafile: TBdatafileMySuffix;

    constructor(
        private tBdatafileService: TBdatafileMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tBdatafileService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'tBdatafileListModification',
                content: 'Deleted an tBdatafile'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-t-bdatafile-my-suffix-delete-popup',
    template: ''
})
export class TBdatafileMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tBdatafilePopupService: TBdatafileMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.tBdatafilePopupService
                .open(TBdatafileMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
