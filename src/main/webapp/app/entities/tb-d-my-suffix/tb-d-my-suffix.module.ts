import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhoraSharedModule } from '../../shared';
import {
    TbDMySuffixService,
    TbDMySuffixPopupService,
    TbDMySuffixComponent,
    TbDMySuffixDetailComponent,
    TbDMySuffixDialogComponent,
    TbDMySuffixPopupComponent,
    TbDMySuffixDeletePopupComponent,
    TbDMySuffixDeleteDialogComponent,
    tbDRoute,
    tbDPopupRoute,
    TbDMySuffixResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...tbDRoute,
    ...tbDPopupRoute,
];

@NgModule({
    imports: [
        JhoraSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TbDMySuffixComponent,
        TbDMySuffixDetailComponent,
        TbDMySuffixDialogComponent,
        TbDMySuffixDeleteDialogComponent,
        TbDMySuffixPopupComponent,
        TbDMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        TbDMySuffixComponent,
        TbDMySuffixDialogComponent,
        TbDMySuffixPopupComponent,
        TbDMySuffixDeleteDialogComponent,
        TbDMySuffixDeletePopupComponent,
    ],
    providers: [
        TbDMySuffixService,
        TbDMySuffixPopupService,
        TbDMySuffixResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhoraTbDMySuffixModule {}
