import { BaseEntity } from './../../shared';

export class TbDMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public fileName?: string,
        public fileType?: string,
    ) {
    }
}
