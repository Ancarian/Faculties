import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ProfileComponent} from './profile/profile.component';
import { UserComponent } from './user/user.component';
import { UserListComponent } from './user-list/user-list.component';
import {UserRoutingModule} from './user-routing.module';
import {SharedModule} from '../../shared/shared.module';
import {UserService} from '../../shared/services/user.service';
import {FileUploadService} from '../../shared/services/fileUpload.service';
import { ChangeEmailComponent } from './change-email/change-email.component';
import { ChangePasswordComponent } from './change-password/change-password.component';

import { SettingsComponent } from './settings/settings.component';
import {ReactiveFormsModule} from '@angular/forms';
import {RequestService} from '../../shared/services/request.service';

@NgModule({
  imports: [
    CommonModule, UserRoutingModule, SharedModule, ReactiveFormsModule
  ],
  declarations: [UserComponent, UserListComponent, ProfileComponent, ChangeEmailComponent, ChangePasswordComponent, SettingsComponent],
  providers: [UserService, FileUploadService, RequestService]
})
export class UserModule { }
