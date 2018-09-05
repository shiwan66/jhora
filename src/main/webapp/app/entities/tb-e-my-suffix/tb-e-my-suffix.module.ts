import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhoraSharedModule } from '../../shared';
import {
    TbEMySuffixService,
    TbEMySuffixPopupService,
    TbEMySuffixComponent,
    TbEMySuffixDetailComponent,
    TbEMySuffixDialogComponent,
    TbEMySuffixPopupComponent,
    TbEMySuffixDeletePopupComponent,
    TbEMySuffixDeleteDialogComponent,
    tbERoute,
    tbEPopupRoute,
} from './';

const ENTITY_STATES = [
    ...tbERoute,
    ...tbEPopupRoute,
];

@NgModule({
    imports: [
        JhoraSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TbEMySuffixComponent,
        TbEMySuffixDetailComponent,
        TbEMySuffixDialogComponent,
        TbEMySuffixDeleteDialogComponent,
        TbEMySuffixPopupComponent,
        TbEMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        TbEMySuffixComponent,
        TbEMySuffixDialogComponent,
        TbEMySuffixPopupComponent,
        TbEMySuffixDeleteDialogComponent,
        TbEMySuffixDeletePopupComponent,
    ],
    providers: [
        TbEMySuffixService,
        TbEMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhoraTbEMySuffixModule {}
