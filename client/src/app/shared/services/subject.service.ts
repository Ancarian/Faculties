import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {JwtService} from './jwt.service';

@Injectable()
export class SubjectService {
  private apiUrl = 'http://localhost:9000/subjects';

  constructor(private http: HttpClient, private jwtService: JwtService) {
  }

  public findAll(): Observable<any> {
    return this.http.get(this.apiUrl);
  }

  getByInvendatoryIds(inventoryIds: number[]): Observable<any> {
    return this.http.put(this.apiUrl + '/indendatory_ids', inventoryIds, {headers: this.jwtService.getHeaders()});
  }
}
