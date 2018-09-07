import { BaseEntity } from './../../shared';

export class MaskMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public ch?: string,
        public dybh?: string,
        public sjqhb?: string,
        public sjcd?: string,
        public zs?: number,
        public crtxbh1?: string,
        public crtxbh2?: string,
        public cdcstxwhiteblack?: string,
        public cdcstxmbcc?: number,
        public cdcstxgc?: number,
        public qxdx?: number,
        public qxmd?: number,
        public tzjd?: number,
        public remark?: string,
        public aaa?: number,
    ) {
    }
}
