import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {JwtService} from './jwt.service';
import {Group} from '../models/model';

@Injectable()
export class FileUploadService {

  private apiUrl = 'http://localhost:9000/image';
  private headers: HttpHeaders = new HttpHeaders({
    Authorization: `Bearer ${localStorage.getItem('token')}`
  });

  constructor(private http: HttpClient) {
  }

  postFile(fileToUpload: File): Observable<any> {
    const formData: FormData = new FormData();
    formData.append('upload', fileToUpload);
    return this.http.post(this.apiUrl, formData, {headers: this.headers});
  }

}
