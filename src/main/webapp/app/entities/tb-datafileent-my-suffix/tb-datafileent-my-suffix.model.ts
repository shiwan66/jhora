import { BaseEntity } from './../../shared';

export class TbDatafileentMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public fileName?: string,
        public fileType?: string,
    ) {
    }
}
