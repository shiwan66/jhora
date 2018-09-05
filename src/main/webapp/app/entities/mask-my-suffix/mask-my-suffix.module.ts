import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhoraSharedModule } from '../../shared';
import {
    MaskMySuffixService,
    MaskMySuffixPopupService,
    MaskMySuffixComponent,
    MaskMySuffixDetailComponent,
    MaskMySuffixDialogComponent,
    MaskMySuffixPopupComponent,
    MaskMySuffixDeletePopupComponent,
    MaskMySuffixDeleteDialogComponent,
    maskRoute,
    maskPopupRoute,
    MaskMySuffixResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...maskRoute,
    ...maskPopupRoute,
];

@NgModule({
    imports: [
        JhoraSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        MaskMySuffixComponent,
        MaskMySuffixDetailComponent,
        MaskMySuffixDialogComponent,
        MaskMySuffixDeleteDialogComponent,
        MaskMySuffixPopupComponent,
        MaskMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        MaskMySuffixComponent,
        MaskMySuffixDialogComponent,
        MaskMySuffixPopupComponent,
        MaskMySuffixDeleteDialogComponent,
        MaskMySuffixDeletePopupComponent,
    ],
    providers: [
        MaskMySuffixService,
        MaskMySuffixPopupService,
        MaskMySuffixResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhoraMaskMySuffixModule {}
