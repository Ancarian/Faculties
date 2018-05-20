import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {LoginComponent} from './login/login.component';
import {RegistrationComponent} from './registration/registration.component';

const routes: Routes = [{path: 'sign-in', component: LoginComponent},
                        {path: 'sign-up', component: RegistrationComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class SignRoutingModule {
}
