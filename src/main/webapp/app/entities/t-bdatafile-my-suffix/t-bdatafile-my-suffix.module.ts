import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhoraSharedModule } from '../../shared';
import {
    TBdatafileMySuffixService,
    TBdatafileMySuffixPopupService,
    TBdatafileMySuffixComponent,
    TBdatafileMySuffixDetailComponent,
    TBdatafileMySuffixDialogComponent,
    TBdatafileMySuffixPopupComponent,
    TBdatafileMySuffixDeletePopupComponent,
    TBdatafileMySuffixDeleteDialogComponent,
    tBdatafileRoute,
    tBdatafilePopupRoute,
    TBdatafileMySuffixResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...tBdatafileRoute,
    ...tBdatafilePopupRoute,
];

@NgModule({
    imports: [
        JhoraSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        TBdatafileMySuffixComponent,
        TBdatafileMySuffixDetailComponent,
        TBdatafileMySuffixDialogComponent,
        TBdatafileMySuffixDeleteDialogComponent,
        TBdatafileMySuffixPopupComponent,
        TBdatafileMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        TBdatafileMySuffixComponent,
        TBdatafileMySuffixDialogComponent,
        TBdatafileMySuffixPopupComponent,
        TBdatafileMySuffixDeleteDialogComponent,
        TBdatafileMySuffixDeletePopupComponent,
    ],
    providers: [
        TBdatafileMySuffixService,
        TBdatafileMySuffixPopupService,
        TBdatafileMySuffixResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhoraTBdatafileMySuffixModule {}
