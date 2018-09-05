import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhoraSharedModule } from '../../shared';
import {
    TbDatafileentMySuffixService,
    TbDatafileentMySuffixPopupService,
    TbDatafileentMySuffixComponent,
    TbDatafileentMySuffixDetailComponent,
    TbDatafileentMySuffixDialogComponent,
    TbDatafileentMySuffixPopupComponent,
    TbDatafileentMySuffixDeletePopupComponent,
    TbDatafileentMySuffixDeleteDialogComponent,
    tbDatafileentRoute,
    tbDatafileentPopupRoute,
    TbDatafileentMySuffixResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...tbDatafileentRoute,
    ...tbDatafileentPopupRoute,
];

@NgModule({
    imports: [
        JhoraSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TbDatafileentMySuffixComponent,
        TbDatafileentMySuffixDetailComponent,
        TbDatafileentMySuffixDialogComponent,
        TbDatafileentMySuffixDeleteDialogComponent,
        TbDatafileentMySuffixPopupComponent,
        TbDatafileentMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        TbDatafileentMySuffixComponent,
        TbDatafileentMySuffixDialogComponent,
        TbDatafileentMySuffixPopupComponent,
        TbDatafileentMySuffixDeleteDialogComponent,
        TbDatafileentMySuffixDeletePopupComponent,
    ],
    providers: [
        TbDatafileentMySuffixService,
        TbDatafileentMySuffixPopupService,
        TbDatafileentMySuffixResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhoraTbDatafileentMySuffixModule {}
