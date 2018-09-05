import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { TBdatafileMySuffix } from './t-bdatafile-my-suffix.model';
import { TBdatafileMySuffixService } from './t-bdatafile-my-suffix.service';

@Component({
    selector: 'jhi-t-bdatafile-my-suffix-detail',
    templateUrl: './t-bdatafile-my-suffix-detail.component.html'
})
export class TBdatafileMySuffixDetailComponent implements OnInit, OnDestroy {

    tBdatafile: TBdatafileMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private tBdatafileService: TBdatafileMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInTBdatafiles();
    }

    load(id) {
        this.tBdatafileService.find(id)
            .subscribe((tBdatafileResponse: HttpResponse<TBdatafileMySuffix>) => {
                this.tBdatafile = tBdatafileResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInTBdatafiles() {
        this.eventSubscriber = this.eventManager.subscribe(
            'tBdatafileListModification',
            (response) => this.load(this.tBdatafile.id)
        );
    }
}
