import { Routes } from '@angular/router';

import { UserRouteAccessService } from '../../shared';
import { MxpmsSearchEquipmentMySuffixComponent } from './mxpms-search-equipment-my-suffix.component';
import { MxpmsSearchEquipmentMySuffixDetailComponent } from './mxpms-search-equipment-my-suffix-detail.component';
import { MxpmsSearchEquipmentMySuffixPopupComponent } from './mxpms-search-equipment-my-suffix-dialog.component';
import {
    MxpmsSearchEquipmentMySuffixDeletePopupComponent
} from './mxpms-search-equipment-my-suffix-delete-dialog.component';

export const mxpmsSearchEquipmentRoute: Routes = [
    {
        path: 'mxpms-search-equipment-my-suffix',
        component: MxpmsSearchEquipmentMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MxpmsSearchEquipments'
        },
        canActivate: [UserRouteAccessService]
    }, {
        path: 'mxpms-search-equipment-my-suffix/:id',
        component: MxpmsSearchEquipmentMySuffixDetailComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MxpmsSearchEquipments'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const mxpmsSearchEquipmentPopupRoute: Routes = [
    {
        path: 'mxpms-search-equipment-my-suffix-new',
        component: MxpmsSearchEquipmentMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MxpmsSearchEquipments'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'mxpms-search-equipment-my-suffix/:id/edit',
        component: MxpmsSearchEquipmentMySuffixPopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MxpmsSearchEquipments'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    },
    {
        path: 'mxpms-search-equipment-my-suffix/:id/delete',
        component: MxpmsSearchEquipmentMySuffixDeletePopupComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'MxpmsSearchEquipments'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
