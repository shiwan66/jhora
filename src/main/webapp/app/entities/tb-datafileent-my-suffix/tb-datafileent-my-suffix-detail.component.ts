import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { TbDatafileentMySuffix } from './tb-datafileent-my-suffix.model';
import { TbDatafileentMySuffixService } from './tb-datafileent-my-suffix.service';

@Component({
    selector: 'jhi-tb-datafileent-my-suffix-detail',
    templateUrl: './tb-datafileent-my-suffix-detail.component.html'
})
export class TbDatafileentMySuffixDetailComponent implements OnInit, OnDestroy {

    tbDatafileent: TbDatafileentMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private tbDatafileentService: TbDatafileentMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTbDatafileents();
    }

    load(id) {
        this.tbDatafileentService.find(id)
            .subscribe((tbDatafileentResponse: HttpResponse<TbDatafileentMySuffix>) => {
                this.tbDatafileent = tbDatafileentResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTbDatafileents() {
        this.eventSubscriber = this.eventManager.subscribe(
            'tbDatafileentListModification',
            (response) => this.load(this.tbDatafileent.id)
        );
    }
}
