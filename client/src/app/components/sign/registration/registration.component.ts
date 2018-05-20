import {Component, OnInit, ViewChild} from '@angular/core';
import {Registration, UserSubject} from '../../../shared/models/model';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthService} from '../../../shared/services/auth.service';
import {Router} from '@angular/router';
import {environment} from '../../../../environments/environment';


@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  isShowPicker = false;
  messages = environment.auth_error_messages;
  userSubjects: UserSubject[] = [];
  user: Registration = new Registration();
  form: FormGroup;

  constructor(private router: Router, private authService: AuthService) {
  }

  ngOnInit() {
    this.formInit();
  }
  formInit() {
    this.form = new FormGroup({
      'name': new FormControl(this.user.name, [
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(20),
      ]),
      'lastname': new FormControl(this.user.lastname,

        [Validators.required,
          Validators.minLength(4),
          Validators.maxLength(20)]),
      'patronymic': new FormControl(this.user.patronymic,
        [Validators.required,
          Validators.minLength(4),
          Validators.maxLength(20)]),
      'nickname':
        new FormControl(this.user.nickname,
          [Validators.required,
            Validators.minLength(4),
            Validators.maxLength(20)]),
      'email':
        new FormControl(this.user.email,
          [Validators.required,
            Validators.minLength(4),
            Validators.maxLength(20),
          Validators.email]),
      'password':
        new FormControl(this.user.password,
          [Validators.required,
            Validators.minLength(4),
            Validators.maxLength(20)])
    });
  }
  onSubmit() {

      this.user = this.form.value;
      this.user.userSubjects = this.userSubjects;
      this.authService.register(this.user).subscribe(res => {
        this.router.navigateByUrl('/sign-in');
      }, error1 => {

      });

  }

  changeChoice(value: boolean) {
    if (value && this.isShowPicker || !value && !this.isShowPicker) {
      this.isShowPicker = !this.isShowPicker;
    }

  }
}
