import {Component, OnInit} from '@angular/core';
import {environment} from '../../../../environments/environment';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../../shared/services/auth.service';
import {Router} from '@angular/router';
import {LoginRequest} from '../../../shared/models/model';
import {JwtService} from '../../../shared/services/jwt.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginRequest: LoginRequest = new LoginRequest();
  messages = environment.auth_error_messages;
  form: FormGroup;

  constructor(private router: Router, private authService: AuthService, private jwtService: JwtService) {
  }

  ngOnInit() {
    this.formInit();
  }

  formInit() {
    this.form = new FormGroup({
      'username': new FormControl(this.loginRequest.username, [
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(20),
      ]),
      'password': new FormControl(this.loginRequest.password,
        [Validators.required,
          Validators.minLength(4),
          Validators.maxLength(20)])
    });
  }

  onSubmit() {
    this.loginRequest = this.form.value;
    this.authService.login(this.loginRequest).subscribe(res => {
      this.jwtService.saveToken(res);
      this.router.navigateByUrl('/me');
    }, error1 => {

    });
  }

}
