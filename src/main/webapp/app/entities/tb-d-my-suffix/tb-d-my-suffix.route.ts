import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { TbDMySuffixComponent } from './tb-d-my-suffix.component';
import { TbDMySuffixDetailComponent } from './tb-d-my-suffix-detail.component';
import { TbDMySuffixPopupComponent } from './tb-d-my-suffix-dialog.component';
import { TbDMySuffixDeletePopupComponent } from './tb-d-my-suffix-delete-dialog.component';

@Injectable()
export class TbDMySuffixResolvePagingParams implements Resolve<any> {

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

export const tbDRoute: Routes = [
    {
        path: 'tb-d-my-suffix',
        component: TbDMySuffixComponent,
        resolve: {
            'pagingParams': TbDMySuffixResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TbDS'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'tb-d-my-suffix/:id',
        component: TbDMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TbDS'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tbDPopupRoute: Routes = [
    {
        path: 'tb-d-my-suffix-new',
        component: TbDMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TbDS'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tb-d-my-suffix/:id/edit',
        component: TbDMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TbDS'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tb-d-my-suffix/:id/delete',
        component: TbDMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TbDS'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
