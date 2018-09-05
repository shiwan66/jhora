import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { MaskMySuffix } from './mask-my-suffix.model';
import { MaskMySuffixPopupService } from './mask-my-suffix-popup.service';
import { MaskMySuffixService } from './mask-my-suffix.service';

@Component({
    selector: 'jhi-mask-my-suffix-delete-dialog',
    templateUrl: './mask-my-suffix-delete-dialog.component.html'
})
export class MaskMySuffixDeleteDialogComponent {

    mask: MaskMySuffix;

    constructor(
        private maskService: MaskMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.maskService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'maskListModification',
                content: 'Deleted an mask'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-mask-my-suffix-delete-popup',
    template: ''
})
export class MaskMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private maskPopupService: MaskMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.maskPopupService
                .open(MaskMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
