import { BaseEntity } from './../../shared';

export class TbEMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public colFileName?: string,
        public colFileType?: string,
    ) {
    }
}
