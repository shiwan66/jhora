import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { MxpmsSearchEquipmentMySuffix } from './mxpms-search-equipment-my-suffix.model';
import { MxpmsSearchEquipmentMySuffixPopupService } from './mxpms-search-equipment-my-suffix-popup.service';
import { MxpmsSearchEquipmentMySuffixService } from './mxpms-search-equipment-my-suffix.service';

@Component({
    selector: 'jhi-mxpms-search-equipment-my-suffix-delete-dialog',
    templateUrl: './mxpms-search-equipment-my-suffix-delete-dialog.component.html'
})
export class MxpmsSearchEquipmentMySuffixDeleteDialogComponent {

    mxpmsSearchEquipment: MxpmsSearchEquipmentMySuffix;

    constructor(
        private mxpmsSearchEquipmentService: MxpmsSearchEquipmentMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.mxpmsSearchEquipmentService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'mxpmsSearchEquipmentListModification',
                content: 'Deleted an mxpmsSearchEquipment'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-mxpms-search-equipment-my-suffix-delete-popup',
    template: ''
})
export class MxpmsSearchEquipmentMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private mxpmsSearchEquipmentPopupService: MxpmsSearchEquipmentMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.mxpmsSearchEquipmentPopupService
                .open(MxpmsSearchEquipmentMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
