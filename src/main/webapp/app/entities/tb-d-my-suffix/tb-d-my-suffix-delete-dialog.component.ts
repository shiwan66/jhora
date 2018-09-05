import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { TbDMySuffix } from './tb-d-my-suffix.model';
import { TbDMySuffixPopupService } from './tb-d-my-suffix-popup.service';
import { TbDMySuffixService } from './tb-d-my-suffix.service';

@Component({
    selector: 'jhi-tb-d-my-suffix-delete-dialog',
    templateUrl: './tb-d-my-suffix-delete-dialog.component.html'
})
export class TbDMySuffixDeleteDialogComponent {

    tbD: TbDMySuffix;

    constructor(
        private tbDService: TbDMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.tbDService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'tbDListModification',
                content: 'Deleted an tbD'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-tb-d-my-suffix-delete-popup',
    template: ''
})
export class TbDMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private tbDPopupService: TbDMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.tbDPopupService
                .open(TbDMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
