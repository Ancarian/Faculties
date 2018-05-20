import {Injectable} from '@angular/core';

import 'rxjs/add/operator/toPromise';
import {JwtService} from './jwt.service';
import {LoginResponse, Registration, User} from '../models/model';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class AuthService {
  private BASE_URL: String = 'http://localhost:9000/auth';


  constructor(private http: HttpClient, private jwtService: JwtService) {

  }

  login(user: Object): Observable<any> {
    let url: string = `${this.BASE_URL}/login`;
    return this.http.post(url, user, {headers: this.jwtService.getHeaders()});
  }

  logout() {
    this.jwtService.destroyToken();
  }


  me(): Observable<any> {
    const url = this.BASE_URL + '/me';
    return this.http.get(url, {headers: this.jwtService.getHeaders()});
  }

  register(user: Registration): Observable<any> {
    const url = `${this.BASE_URL}/sign_up`;
    return this.http.post(url, user, {headers: this.jwtService.getHeaders()});
  }

  ensureAuthenticated(token): Observable<any> {
    let url: string = `${this.BASE_URL}/me`;
    return this.http.get(url, {headers: this.jwtService.getHeaders()});
  }
}
