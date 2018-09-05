import { BaseEntity } from './../../shared';

export class DBFileMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public fileName?: string,
        public fileType?: string,
        public dataContentType?: string,
        public data?: any,
    ) {
    }
}
