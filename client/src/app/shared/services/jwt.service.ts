import {Injectable} from '@angular/core';
import {isUndefined} from 'util';
import {LoginResponse} from '../models/model';
import {HttpHeaders} from '@angular/common/http';


@Injectable()
export class JwtService {

  getToken(): String {
    return localStorage.getItem('token');
  }

  saveToken(token: LoginResponse) {
    localStorage.setItem('token', token.token);
    const jwtData = token.token.split('.')[1];
    const decodedJwtJsonData = window.atob(jwtData);
    const decodedJwtData = JSON.parse(decodedJwtJsonData);
    localStorage.setItem('roles', decodedJwtData.roles);
  }

  destroyToken() {
    localStorage.removeItem('token');
    localStorage.removeItem('roles');
  }

  isLoggedIn() {
    if (localStorage.getItem('token') != null) {
      return !isUndefined(localStorage.getItem('token'));
    }
    return false;
  }

  isAdmin() {
    if (localStorage.getItem('roles') != null) {
      return localStorage.getItem('roles').includes('ADMIN');
    }
    return false;
  }

  isUser() {
    if (localStorage.getItem('roles') != null) {
      return localStorage.getItem('roles').includes('USER');
    }
    return false;
  }

  isVerified() {
    if (localStorage.getItem('roles') != null) {
      return localStorage.getItem('roles').includes('VERIFIED');
    }
    return false;
  }

  getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Bearer ${localStorage.getItem('token')}`
    });
  }


}
