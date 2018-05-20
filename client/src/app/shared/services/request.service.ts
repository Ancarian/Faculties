import {Observable} from 'rxjs/Observable';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Group} from '../models/model';
import {JwtService} from './jwt.service';
import {Injectable} from '@angular/core';

@Injectable()
export class RequestService {

  private apiUrl = 'http://localhost:9000/requests/';
  private headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${localStorage.getItem('token')}`
  });

  constructor(private http: HttpClient, private jwtService: JwtService) {
  }

  confirm(id: number): Observable<Group[]> {
    return this.http.put(this.apiUrl + id + '/confirm', null, {headers: this.headers})
      .catch((error: any) => console.log(error);
  }

  cancel(id: number): Observable<Group[]> {
    return this.http.put(this.apiUrl + id + '/cancel', null, {headers: this.headers})
      .catch((error: any) => Observable.throw(error || 'Server error'));
  }
}
