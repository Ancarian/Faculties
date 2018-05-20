import {Injectable} from '@angular/core';

import {Observable} from 'rxjs/Observable';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from '../models/model';

@Injectable()
export class UserService {

  private apiUrl = 'http://localhost:9000/users';
  private headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${localStorage.getItem('token')}`
  });

  constructor(private http: HttpClient) {
  }

  selectGroup(id: number): any {
    return this.http.put(this.apiUrl + '/request/' + id, {}, {headers: this.headers});
  }

  findAll(): Observable<any> {
    return this.http.get(this.apiUrl, {headers: this.headers});
  }

  findById(id: number): any {
    return this.http.get(this.apiUrl + '/id/' + id, {headers: this.headers});
  }

  saveUser(user: User): Observable<any> {
    return this.http.post(this.apiUrl, user)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  deleteUserById(id: number) {
    return this.http.delete(this.apiUrl + '/' + id, {headers: this.headers});
  }

  updateUser(user: User): Observable<any> {
    return this.http.put(this.apiUrl + '/', user, {headers: this.headers});
  }

  sendRequest(id: number) {
    return this.http.put('http://localhost:9000/users/request/' + id, '', {headers: this.headers});
  }

  getRequests() {
    return this.http.get('http://localhost:9000/requests', {headers: this.headers});
  }
}
