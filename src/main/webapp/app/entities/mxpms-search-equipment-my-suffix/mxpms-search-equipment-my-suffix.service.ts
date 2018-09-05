import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { MxpmsSearchEquipmentMySuffix } from './mxpms-search-equipment-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<MxpmsSearchEquipmentMySuffix>;

@Injectable()
export class MxpmsSearchEquipmentMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/mxpms-search-equipments';

    constructor(private http: HttpClient) { }

    create(mxpmsSearchEquipment: MxpmsSearchEquipmentMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(mxpmsSearchEquipment);
        return this.http.post<MxpmsSearchEquipmentMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(mxpmsSearchEquipment: MxpmsSearchEquipmentMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(mxpmsSearchEquipment);
        return this.http.put<MxpmsSearchEquipmentMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<MxpmsSearchEquipmentMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<MxpmsSearchEquipmentMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<MxpmsSearchEquipmentMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<MxpmsSearchEquipmentMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: MxpmsSearchEquipmentMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<MxpmsSearchEquipmentMySuffix[]>): HttpResponse<MxpmsSearchEquipmentMySuffix[]> {
        const jsonResponse: MxpmsSearchEquipmentMySuffix[] = res.body;
        const body: MxpmsSearchEquipmentMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to MxpmsSearchEquipmentMySuffix.
     */
    private convertItemFromServer(mxpmsSearchEquipment: MxpmsSearchEquipmentMySuffix): MxpmsSearchEquipmentMySuffix {
        const copy: MxpmsSearchEquipmentMySuffix = Object.assign({}, mxpmsSearchEquipment);
        return copy;
    }

    /**
     * Convert a MxpmsSearchEquipmentMySuffix to a JSON which can be sent to the server.
     */
    private convert(mxpmsSearchEquipment: MxpmsSearchEquipmentMySuffix): MxpmsSearchEquipmentMySuffix {
        const copy: MxpmsSearchEquipmentMySuffix = Object.assign({}, mxpmsSearchEquipment);
        return copy;
    }
}
