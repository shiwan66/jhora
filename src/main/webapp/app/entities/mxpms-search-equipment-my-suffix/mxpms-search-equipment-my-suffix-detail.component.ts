import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { MxpmsSearchEquipmentMySuffix } from './mxpms-search-equipment-my-suffix.model';
import { MxpmsSearchEquipmentMySuffixService } from './mxpms-search-equipment-my-suffix.service';

@Component({
    selector: 'jhi-mxpms-search-equipment-my-suffix-detail',
    templateUrl: './mxpms-search-equipment-my-suffix-detail.component.html'
})
export class MxpmsSearchEquipmentMySuffixDetailComponent implements OnInit, OnDestroy {

    mxpmsSearchEquipment: MxpmsSearchEquipmentMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private mxpmsSearchEquipmentService: MxpmsSearchEquipmentMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMxpmsSearchEquipments();
    }

    load(id) {
        this.mxpmsSearchEquipmentService.find(id)
            .subscribe((mxpmsSearchEquipmentResponse: HttpResponse<MxpmsSearchEquipmentMySuffix>) => {
                this.mxpmsSearchEquipment = mxpmsSearchEquipmentResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMxpmsSearchEquipments() {
        this.eventSubscriber = this.eventManager.subscribe(
            'mxpmsSearchEquipmentListModification',
            (response) => this.load(this.mxpmsSearchEquipment.id)
        );
    }
}
