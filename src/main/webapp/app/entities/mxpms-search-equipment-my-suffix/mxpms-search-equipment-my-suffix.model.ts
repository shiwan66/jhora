import { BaseEntity } from './../../shared';

export class MxpmsSearchEquipmentMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public objId?: string,
        public name?: string,
        public pid?: string,
        public haschildren?: string,
        public imgurl?: string,
        public treeid?: string,
        public exvalue?: string,
        public nodemodel?: string,
        public itemtype?: string,
        public orderid?: string,
        public nodewhbz?: string,
        public nodeyxzt?: string,
        public orgid?: string,
        public bzname?: string,
    ) {
    }
}
