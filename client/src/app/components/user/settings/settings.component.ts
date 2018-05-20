import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Registration, User} from '../../../shared/models/model';
import {UserService} from '../../../shared/services/user.service';
import {environment} from '../../../../environments/environment';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css']
})
export class SettingsComponent implements OnInit {
  form: FormGroup;
  messages = environment.auth_error_messages;
  @Input() user: User;
  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.formInit();
  }

  formInit() {
    this.form = new FormGroup({
      'name': new FormControl(this.user.name, [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(20),
      ]),
      'lastname': new FormControl(this.user.lastname,
        [Validators.required,
          Validators.minLength(6),
          Validators.maxLength(20)]),
      'patronymic': new FormControl(this.user.patronymic,
        [Validators.required,
          Validators.minLength(6),
          Validators.maxLength(20)])
    });
  }

  onSubmit() {

    this.user.name = this.form.get('name').value;
    this.user.lastname = this.form.get('lastname').value;
    this.user.patronymic = this.form.get('patronymic').value;
    console.log(this.user);
    this.userService.updateUser(this.user).subscribe(res => {

    }, error1 => {
        console.log(error1);
    });

  }

}
