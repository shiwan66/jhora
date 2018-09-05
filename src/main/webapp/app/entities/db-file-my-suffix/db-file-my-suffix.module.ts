import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhoraSharedModule } from '../../shared';
import {
    DBFileMySuffixService,
    DBFileMySuffixPopupService,
    DBFileMySuffixComponent,
    DBFileMySuffixDetailComponent,
    DBFileMySuffixDialogComponent,
    DBFileMySuffixPopupComponent,
    DBFileMySuffixDeletePopupComponent,
    DBFileMySuffixDeleteDialogComponent,
    dBFileRoute,
    dBFilePopupRoute,
    DBFileMySuffixResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...dBFileRoute,
    ...dBFilePopupRoute,
];

@NgModule({
    imports: [
        JhoraSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        DBFileMySuffixComponent,
        DBFileMySuffixDetailComponent,
        DBFileMySuffixDialogComponent,
        DBFileMySuffixDeleteDialogComponent,
        DBFileMySuffixPopupComponent,
        DBFileMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        DBFileMySuffixComponent,
        DBFileMySuffixDialogComponent,
        DBFileMySuffixPopupComponent,
        DBFileMySuffixDeleteDialogComponent,
        DBFileMySuffixDeletePopupComponent,
    ],
    providers: [
        DBFileMySuffixService,
        DBFileMySuffixPopupService,
        DBFileMySuffixResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhoraDBFileMySuffixModule {}
