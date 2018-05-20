import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {LoginComponent} from '../sign/login/login.component';
import {RegistrationComponent} from '../sign/registration/registration.component';
import {ProfileComponent} from './profile/profile.component';
import {UserListComponent} from './user-list/user-list.component';
import {UserComponent} from './user/user.component';

const routes: Routes = [{path: 'me', component: ProfileComponent},
  {path: 'users', component: UserListComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class UserRoutingModule {
}
