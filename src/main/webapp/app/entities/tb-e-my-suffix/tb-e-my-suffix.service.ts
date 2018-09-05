import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { TbEMySuffix } from './tb-e-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<TbEMySuffix>;

@Injectable()
export class TbEMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/tb-es';

    constructor(private http: HttpClient) { }

    create(tbE: TbEMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(tbE);
        return this.http.post<TbEMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(tbE: TbEMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(tbE);
        return this.http.put<TbEMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<TbEMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<TbEMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<TbEMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<TbEMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: TbEMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<TbEMySuffix[]>): HttpResponse<TbEMySuffix[]> {
        const jsonResponse: TbEMySuffix[] = res.body;
        const body: TbEMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to TbEMySuffix.
     */
    private convertItemFromServer(tbE: TbEMySuffix): TbEMySuffix {
        const copy: TbEMySuffix = Object.assign({}, tbE);
        return copy;
    }

    /**
     * Convert a TbEMySuffix to a JSON which can be sent to the server.
     */
    private convert(tbE: TbEMySuffix): TbEMySuffix {
        const copy: TbEMySuffix = Object.assign({}, tbE);
        return copy;
    }
}
