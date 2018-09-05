import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { TbDMySuffix } from './tb-d-my-suffix.model';
import { TbDMySuffixService } from './tb-d-my-suffix.service';

@Component({
    selector: 'jhi-tb-d-my-suffix-detail',
    templateUrl: './tb-d-my-suffix-detail.component.html'
})
export class TbDMySuffixDetailComponent implements OnInit, OnDestroy {

    tbD: TbDMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private tbDService: TbDMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTbDS();
    }

    load(id) {
        this.tbDService.find(id)
            .subscribe((tbDResponse: HttpResponse<TbDMySuffix>) => {
                this.tbD = tbDResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTbDS() {
        this.eventSubscriber = this.eventManager.subscribe(
            'tbDListModification',
            (response) => this.load(this.tbD.id)
        );
    }
}
