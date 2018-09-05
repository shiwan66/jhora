import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { TbDatafileentMySuffix } from './tb-datafileent-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<TbDatafileentMySuffix>;

@Injectable()
export class TbDatafileentMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/tb-datafileents';

    constructor(private http: HttpClient) { }

    create(tbDatafileent: TbDatafileentMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(tbDatafileent);
        return this.http.post<TbDatafileentMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(tbDatafileent: TbDatafileentMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(tbDatafileent);
        return this.http.put<TbDatafileentMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<TbDatafileentMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<TbDatafileentMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<TbDatafileentMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<TbDatafileentMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: TbDatafileentMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<TbDatafileentMySuffix[]>): HttpResponse<TbDatafileentMySuffix[]> {
        const jsonResponse: TbDatafileentMySuffix[] = res.body;
        const body: TbDatafileentMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to TbDatafileentMySuffix.
     */
    private convertItemFromServer(tbDatafileent: TbDatafileentMySuffix): TbDatafileentMySuffix {
        const copy: TbDatafileentMySuffix = Object.assign({}, tbDatafileent);
        return copy;
    }

    /**
     * Convert a TbDatafileentMySuffix to a JSON which can be sent to the server.
     */
    private convert(tbDatafileent: TbDatafileentMySuffix): TbDatafileentMySuffix {
        const copy: TbDatafileentMySuffix = Object.assign({}, tbDatafileent);
        return copy;
    }
}
