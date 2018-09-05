import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { MaskMySuffix } from './mask-my-suffix.model';
import { MaskMySuffixService } from './mask-my-suffix.service';

@Component({
    selector: 'jhi-mask-my-suffix-detail',
    templateUrl: './mask-my-suffix-detail.component.html'
})
export class MaskMySuffixDetailComponent implements OnInit, OnDestroy {

    mask: MaskMySuffix;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private maskService: MaskMySuffixService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInMasks();
    }

    load(id) {
        this.maskService.find(id)
            .subscribe((maskResponse: HttpResponse<MaskMySuffix>) => {
                this.mask = maskResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInMasks() {
        this.eventSubscriber = this.eventManager.subscribe(
            'maskListModification',
            (response) => this.load(this.mask.id)
        );
    }
}
