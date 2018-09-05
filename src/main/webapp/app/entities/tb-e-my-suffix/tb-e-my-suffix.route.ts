import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { TbEMySuffixComponent } from './tb-e-my-suffix.component';
import { TbEMySuffixDetailComponent } from './tb-e-my-suffix-detail.component';
import { TbEMySuffixPopupComponent } from './tb-e-my-suffix-dialog.component';
import { TbEMySuffixDeletePopupComponent } from './tb-e-my-suffix-delete-dialog.component';

export const tbERoute: Routes = [
    {
        path: 'tb-e-my-suffix',
        component: TbEMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TbES'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'tb-e-my-suffix/:id',
        component: TbEMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TbES'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tbEPopupRoute: Routes = [
    {
        path: 'tb-e-my-suffix-new',
        component: TbEMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TbES'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tb-e-my-suffix/:id/edit',
        component: TbEMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TbES'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'tb-e-my-suffix/:id/delete',
        component: TbEMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'TbES'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
