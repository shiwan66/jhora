import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { MaskMySuffix } from './mask-my-suffix.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<MaskMySuffix>;

@Injectable()
export class MaskMySuffixService {

    private resourceUrl =  SERVER_API_URL + 'api/masks';

    constructor(private http: HttpClient) { }

    create(mask: MaskMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(mask);
        return this.http.post<MaskMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(mask: MaskMySuffix): Observable<EntityResponseType> {
        const copy = this.convert(mask);
        return this.http.put<MaskMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<MaskMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<MaskMySuffix[]>> {
        const options = createRequestOption(req);
        return this.http.get<MaskMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<MaskMySuffix[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: MaskMySuffix = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<MaskMySuffix[]>): HttpResponse<MaskMySuffix[]> {
        const jsonResponse: MaskMySuffix[] = res.body;
        const body: MaskMySuffix[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to MaskMySuffix.
     */
    private convertItemFromServer(mask: MaskMySuffix): MaskMySuffix {
        const copy: MaskMySuffix = Object.assign({}, mask);
        return copy;
    }

    /**
     * Convert a MaskMySuffix to a JSON which can be sent to the server.
     */
    private convert(mask: MaskMySuffix): MaskMySuffix {
        const copy: MaskMySuffix = Object.assign({}, mask);
        return copy;
    }
}
