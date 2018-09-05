import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { MaskMySuffixComponent } from './mask-my-suffix.component';
import { MaskMySuffixDetailComponent } from './mask-my-suffix-detail.component';
import { MaskMySuffixPopupComponent } from './mask-my-suffix-dialog.component';
import { MaskMySuffixDeletePopupComponent } from './mask-my-suffix-delete-dialog.component';

@Injectable()
export class MaskMySuffixResolvePagingParams implements Resolve<any> {

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

export const maskRoute: Routes = [
    {
        path: 'mask-my-suffix',
        component: MaskMySuffixComponent,
        resolve: {
            'pagingParams': MaskMySuffixResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Masks'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'mask-my-suffix/:id',
        component: MaskMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Masks'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const maskPopupRoute: Routes = [
    {
        path: 'mask-my-suffix-new',
        component: MaskMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Masks'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'mask-my-suffix/:id/edit',
        component: MaskMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Masks'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'mask-my-suffix/:id/delete',
        component: MaskMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Masks'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
