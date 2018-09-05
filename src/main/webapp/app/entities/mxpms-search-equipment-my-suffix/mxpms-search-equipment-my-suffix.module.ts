import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhoraSharedModule } from '../../shared';
import {
    MxpmsSearchEquipmentMySuffixService,
    MxpmsSearchEquipmentMySuffixPopupService,
    MxpmsSearchEquipmentMySuffixComponent,
    MxpmsSearchEquipmentMySuffixDetailComponent,
    MxpmsSearchEquipmentMySuffixDialogComponent,
    MxpmsSearchEquipmentMySuffixPopupComponent,
    MxpmsSearchEquipmentMySuffixDeletePopupComponent,
    MxpmsSearchEquipmentMySuffixDeleteDialogComponent,
    mxpmsSearchEquipmentRoute,
    mxpmsSearchEquipmentPopupRoute,
} from './';

const ENTITY_STATES = [
    ...mxpmsSearchEquipmentRoute,
    ...mxpmsSearchEquipmentPopupRoute,
];

@NgModule({
    imports: [
        JhoraSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        MxpmsSearchEquipmentMySuffixComponent,
        MxpmsSearchEquipmentMySuffixDetailComponent,
        MxpmsSearchEquipmentMySuffixDialogComponent,
        MxpmsSearchEquipmentMySuffixDeleteDialogComponent,
        MxpmsSearchEquipmentMySuffixPopupComponent,
        MxpmsSearchEquipmentMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        MxpmsSearchEquipmentMySuffixComponent,
        MxpmsSearchEquipmentMySuffixDialogComponent,
        MxpmsSearchEquipmentMySuffixPopupComponent,
        MxpmsSearchEquipmentMySuffixDeleteDialogComponent,
        MxpmsSearchEquipmentMySuffixDeletePopupComponent,
    ],
    providers: [
        MxpmsSearchEquipmentMySuffixService,
        MxpmsSearchEquipmentMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhoraMxpmsSearchEquipmentMySuffixModule {}
