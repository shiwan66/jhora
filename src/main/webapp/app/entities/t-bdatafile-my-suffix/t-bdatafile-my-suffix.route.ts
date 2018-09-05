import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { TBdatafileMySuffixComponent } from './t-bdatafile-my-suffix.component';
import { TBdatafileMySuffixDetailComponent } from './t-bdatafile-my-suffix-detail.component';
import { TBdatafileMySuffixPopupComponent } from './t-bdatafile-my-suffix-dialog.component';
import { TBdatafileMySuffixDeletePopupComponent } from './t-bdatafile-my-suffix-delete-dialog.component';

@Injectable()
export class TBdatafileMySuffixResolvePagingParams implements Resolve<any> {

    constructor(private paginationUtil: JhiPaginationUtil) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
        const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
        return {
            page: this.paginationUtil.parsePage(page),
            predicate: this.paginationUtil.parsePredicate(sort),
            ascending: this.paginationUtil.parseAscending(sort)
      };
    }
}

export const tBdatafileRoute: Routes = [
    {
        path: 't-bdatafile-my-suffix',
        component: TBdatafileMySuffixComponent,
        resolve: {
            'pagingParams': TBdatafileMySuffixResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TBdatafiles'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 't-bdatafile-my-suffix/:id',
        component: TBdatafileMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TBdatafiles'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tBdatafilePopupRoute: Routes = [
    {
        path: 't-bdatafile-my-suffix-new',
        component: TBdatafileMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TBdatafiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 't-bdatafile-my-suffix/:id/edit',
        component: TBdatafileMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TBdatafiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 't-bdatafile-my-suffix/:id/delete',
        component: TBdatafileMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TBdatafiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
