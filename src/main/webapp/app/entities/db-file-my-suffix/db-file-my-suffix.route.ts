import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil } from 'ng-jhipster';

import { UserRouteAccessService } from '../../shared';
import { DBFileMySuffixComponent } from './db-file-my-suffix.component';
import { DBFileMySuffixDetailComponent } from './db-file-my-suffix-detail.component';
import { DBFileMySuffixPopupComponent } from './db-file-my-suffix-dialog.component';
import { DBFileMySuffixDeletePopupComponent } from './db-file-my-suffix-delete-dialog.component';

@Injectable()
export class DBFileMySuffixResolvePagingParams implements Resolve<any> {

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

export const dBFileRoute: Routes = [
    {
        path: 'db-file-my-suffix',
        component: DBFileMySuffixComponent,
        resolve: {
            'pagingParams': DBFileMySuffixResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DBFiles'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'db-file-my-suffix/:id',
        component: DBFileMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DBFiles'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const dBFilePopupRoute: Routes = [
    {
        path: 'db-file-my-suffix-new',
        component: DBFileMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DBFiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'db-file-my-suffix/:id/edit',
        component: DBFileMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DBFiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'db-file-my-suffix/:id/delete',
        component: DBFileMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'DBFiles'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
