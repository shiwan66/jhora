import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhoraDBFileMySuffixModule } from './db-file-my-suffix/db-file-my-suffix.module';
import { JhoraMxpmsSearchEquipmentMySuffixModule } from './mxpms-search-equipment-my-suffix/mxpms-search-equipment-my-suffix.module';
import { JhoraMaskMySuffixModule } from './mask-my-suffix/mask-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        JhoraDBFileMySuffixModule,
        JhoraMxpmsSearchEquipmentMySuffixModule,
        JhoraMaskMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhoraEntityModule {}
