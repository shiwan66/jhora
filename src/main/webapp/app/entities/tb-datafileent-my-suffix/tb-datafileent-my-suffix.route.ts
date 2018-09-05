import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { TbDatafileentMySuffixComponent } from './tb-datafileent-my-suffix.component';
import { TbDatafileentMySuffixDetailComponent } from './tb-datafileent-my-suffix-detail.component';
import { TbDatafileentMySuffixPopupComponent } from './tb-datafileent-my-suffix-dialog.component';
import { TbDatafileentMySuffixDeletePopupComponent } from './tb-datafileent-my-suffix-delete-dialog.component';

@Injectable()
export class TbDatafileentMySuffixResolvePagingParams implements Resolve<any> {

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

export const tbDatafileentRoute: Routes = [
    {
        path: 'tb-datafileent-my-suffix',
        component: TbDatafileentMySuffixComponent,
        resolve: {
            'pagingParams': TbDatafileentMySuffixResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TbDatafileents'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'tb-datafileent-my-suffix/:id',
        component: TbDatafileentMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TbDatafileents'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tbDatafileentPopupRoute: Routes = [
    {
        path: 'tb-datafileent-my-suffix-new',
        component: TbDatafileentMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TbDatafileents'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tb-datafileent-my-suffix/:id/edit',
        component: TbDatafileentMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TbDatafileents'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tb-datafileent-my-suffix/:id/delete',
        component: TbDatafileentMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TbDatafileents'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
