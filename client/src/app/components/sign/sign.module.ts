import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {SignRoutingModule} from './sign-routing.module';
import {LoginComponent} from './login/login.component';
import {RegistrationComponent} from './registration/registration.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AuthService} from '../../shared/services/auth.service';
import {SharedModule} from '../../shared/shared.module';

@NgModule({
  imports: [
    CommonModule,
    SignRoutingModule, ReactiveFormsModule, SharedModule
  ],
  declarations: [LoginComponent, RegistrationComponent],
  providers: [AuthService]
})
export class SignModule {
}
