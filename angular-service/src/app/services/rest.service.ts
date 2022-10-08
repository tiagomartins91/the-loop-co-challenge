import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpResponse, HttpParams } from '@angular/common/http';
import { CommonService } from './common/common.service';

type EntityResponseType = HttpResponse<any>;

@Injectable({
  providedIn: 'root'
})
export class RestService {

  resourceUrl: string;

  constructor(private common: CommonService, private http: HttpClient) {
    this.resourceUrl = `${this.common.apiUrl}`;
  }

  queryPrices(req?: any): Observable<EntityResponseType> {
    const options = createQueryParams(req);
    return this.http.get<any>(`${this.resourceUrl}prices/search`, { params: options, observe: 'response' });
  }

 }

 export const createQueryParams = (req?: any): HttpParams => {
  let options: HttpParams = new HttpParams();
  if (req) {
    Object.keys(req).forEach(key => {
      options = options.append(key, req[key]);
    });
  }
  return options;
};
