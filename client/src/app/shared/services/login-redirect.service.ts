import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthService } from './auth.service';
import {JwtService} from './jwt.service';


@Injectable()
export class LoginRedirect implements CanActivate {
  constructor(private jwtService: JwtService, private router: Router) {}
  canActivate(): boolean {
    if (this.jwtService.isLoggedIn()) {
      this.router.navigateByUrl('/profile');
      return false;
    } else {
      return true;
    }
  }
}
