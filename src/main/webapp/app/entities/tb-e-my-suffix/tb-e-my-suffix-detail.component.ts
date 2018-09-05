import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { TbEMySuffix } from './tb-e-my-suffix.model';
import { TbEMySuffixService } from './tb-e-my-suffix.service';

@Component({
    selector: 'jhi-tb-e-my-suffix-detail',
    templateUrl: './tb-e-my-suffix-detail.component.html'
})
export class TbEMySuffixDetailComponent implements OnInit, OnDestroy {

    tbE: TbEMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private tbEService: TbEMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTbES();
    }

    load(id) {
        this.tbEService.find(id)
            .subscribe((tbEResponse: HttpResponse<TbEMySuffix>) => {
                this.tbE = tbEResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTbES() {
        this.eventSubscriber = this.eventManager.subscribe(
            'tbEListModification',
            (response) => this.load(this.tbE.id)
        );
    }
}
