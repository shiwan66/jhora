import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TbEMySuffix } from './tb-e-my-suffix.model';
import { TbEMySuffixPopupService } from './tb-e-my-suffix-popup.service';
import { TbEMySuffixService } from './tb-e-my-suffix.service';

@Component({
    selector: 'jhi-tb-e-my-suffix-delete-dialog',
    templateUrl: './tb-e-my-suffix-delete-dialog.component.html'
})
export class TbEMySuffixDeleteDialogComponent {

    tbE: TbEMySuffix;

    constructor(
        private tbEService: TbEMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tbEService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'tbEListModification',
                content: 'Deleted an tbE'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tb-e-my-suffix-delete-popup',
    template: ''
})
export class TbEMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tbEPopupService: TbEMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.tbEPopupService
                .open(TbEMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
