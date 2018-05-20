import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {JwtService} from './jwt.service';
import {Observable} from 'rxjs/Observable';
import {Faculty, Group, University} from '../models/model';


@Injectable()
export class UniversityService {
  private apiUrl = 'http://localhost:9000/universities/';


  constructor(private http: HttpClient, private jwtService: JwtService) {
  }

  findAll(): Observable<University[]> {
    return this.http.get(this.apiUrl)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  findById(id: number): Observable<University> {
    return this.http.get(this.apiUrl + id)
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }
}
