import {Injectable} from '@angular/core';

import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Group} from '../models/model';
import {JwtService} from './jwt.service';


@Injectable()
export class GroupService {

  private apiUrl = 'http://localhost:9000/groups/';


  constructor(private http: HttpClient, private jwtService: JwtService) {
  }

  findAll(): Observable<Group[]> {
    return this.http.get(this.apiUrl)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  findById(id: number): Observable<any> {
    return this.http.get(this.apiUrl + id)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  saveGroup(group: Group): Observable<any> {
    return this.http.post(this.apiUrl, group, {headers: this.jwtService.getHeaders()})
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  deleteById(id: number) {
    return this.http.delete(this.apiUrl + id, {headers: this.jwtService.getHeaders()})
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  updateGroup(group: Group): Observable<void> {
    return null;
  }

}
