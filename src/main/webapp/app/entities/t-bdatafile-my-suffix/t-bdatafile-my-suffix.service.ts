import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { TBdatafileMySuffix } from './t-bdatafile-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<TBdatafileMySuffix>;

@Injectable()
export class TBdatafileMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/t-bdatafiles';

    constructor(private http: HttpClient) { }

    create(tBdatafile: TBdatafileMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(tBdatafile);
        return this.http.post<TBdatafileMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(tBdatafile: TBdatafileMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(tBdatafile);
        return this.http.put<TBdatafileMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<TBdatafileMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<TBdatafileMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<TBdatafileMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<TBdatafileMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: TBdatafileMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<TBdatafileMySuffix[]>): HttpResponse<TBdatafileMySuffix[]> {
        const jsonResponse: TBdatafileMySuffix[] = res.body;
        const body: TBdatafileMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to TBdatafileMySuffix.
     */
    private convertItemFromServer(tBdatafile: TBdatafileMySuffix): TBdatafileMySuffix {
        const copy: TBdatafileMySuffix = Object.assign({}, tBdatafile);
        return copy;
    }

    /**
     * Convert a TBdatafileMySuffix to a JSON which can be sent to the server.
     */
    private convert(tBdatafile: TBdatafileMySuffix): TBdatafileMySuffix {
        const copy: TBdatafileMySuffix = Object.assign({}, tBdatafile);
        return copy;
    }
}
