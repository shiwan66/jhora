import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { TbDMySuffix } from './tb-d-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<TbDMySuffix>;

@Injectable()
export class TbDMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/tb-ds';

    constructor(private http: HttpClient) { }

    create(tbD: TbDMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(tbD);
        return this.http.post<TbDMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(tbD: TbDMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(tbD);
        return this.http.put<TbDMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<TbDMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<TbDMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<TbDMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<TbDMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: TbDMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<TbDMySuffix[]>): HttpResponse<TbDMySuffix[]> {
        const jsonResponse: TbDMySuffix[] = res.body;
        const body: TbDMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to TbDMySuffix.
     */
    private convertItemFromServer(tbD: TbDMySuffix): TbDMySuffix {
        const copy: TbDMySuffix = Object.assign({}, tbD);
        return copy;
    }

    /**
     * Convert a TbDMySuffix to a JSON which can be sent to the server.
     */
    private convert(tbD: TbDMySuffix): TbDMySuffix {
        const copy: TbDMySuffix = Object.assign({}, tbD);
        return copy;
    }
}
