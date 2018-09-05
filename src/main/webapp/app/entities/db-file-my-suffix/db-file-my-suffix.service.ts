import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { DBFileMySuffix } from './db-file-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<DBFileMySuffix>;

@Injectable()
export class DBFileMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/db-files';

    constructor(private http: HttpClient) { }

    create(dBFile: DBFileMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(dBFile);
        return this.http.post<DBFileMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(dBFile: DBFileMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(dBFile);
        return this.http.put<DBFileMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<DBFileMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<DBFileMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<DBFileMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<DBFileMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: DBFileMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<DBFileMySuffix[]>): HttpResponse<DBFileMySuffix[]> {
        const jsonResponse: DBFileMySuffix[] = res.body;
        const body: DBFileMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to DBFileMySuffix.
     */
    private convertItemFromServer(dBFile: DBFileMySuffix): DBFileMySuffix {
        const copy: DBFileMySuffix = Object.assign({}, dBFile);
        return copy;
    }

    /**
     * Convert a DBFileMySuffix to a JSON which can be sent to the server.
     */
    private convert(dBFile: DBFileMySuffix): DBFileMySuffix {
        const copy: DBFileMySuffix = Object.assign({}, dBFile);
        return copy;
    }
}
