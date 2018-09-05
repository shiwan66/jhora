import { BaseEntity } from './../../shared';

export class TBdatafileMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public tbFileName?: string,
        public tbFileType?: string,
    ) {
    }
}
