import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { JhoraDBFileMySuffixModule } from './db-file-my-suffix/db-file-my-suffix.module';
import { JhoraRegionMySuffixModule } from './region-my-suffix/region-my-suffix.module';
import { JhoraCountryMySuffixModule } from './country-my-suffix/country-my-suffix.module';
import { JhoraLocationMySuffixModule } from './location-my-suffix/location-my-suffix.module';
import { JhoraDepartmentMySuffixModule } from './department-my-suffix/department-my-suffix.module';
import { JhoraTaskMySuffixModule } from './task-my-suffix/task-my-suffix.module';
import { JhoraEmployeeMySuffixModule } from './employee-my-suffix/employee-my-suffix.module';
import { JhoraJobMySuffixModule } from './job-my-suffix/job-my-suffix.module';
import { JhoraJobHistoryMySuffixModule } from './job-history-my-suffix/job-history-my-suffix.module';
import { JhoraTBdatafileMySuffixModule } from './t-bdatafile-my-suffix/t-bdatafile-my-suffix.module';
import { JhoraTbDatafileentMySuffixModule } from './tb-datafileent-my-suffix/tb-datafileent-my-suffix.module';
import { JhoraTbDMySuffixModule } from './tb-d-my-suffix/tb-d-my-suffix.module';
import { JhoraTbEMySuffixModule } from './tb-e-my-suffix/tb-e-my-suffix.module';
import { JhoraMxpmsSearchEquipmentMySuffixModule } from './mxpms-search-equipment-my-suffix/mxpms-search-equipment-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        JhoraDBFileMySuffixModule,
        JhoraRegionMySuffixModule,
        JhoraCountryMySuffixModule,
        JhoraLocationMySuffixModule,
        JhoraDepartmentMySuffixModule,
        JhoraTaskMySuffixModule,
        JhoraEmployeeMySuffixModule,
        JhoraJobMySuffixModule,
        JhoraJobHistoryMySuffixModule,
        JhoraTBdatafileMySuffixModule,
        JhoraTbDatafileentMySuffixModule,
        JhoraTbDMySuffixModule,
        JhoraTbEMySuffixModule,
        JhoraMxpmsSearchEquipmentMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JhoraEntityModule {}
