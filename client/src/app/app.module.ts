import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {AppComponent} from './app.component';
import {JwtService} from './shared/services/jwt.service';
import {SignModule} from './components/sign/sign.module';
import {IndexModule} from './components/index/index.module';
import {HeaderComponent} from './shared/layout/header/header.component';
import {FooterComponent} from './shared/layout/footer/footer.component';
import {SharedModule} from './shared/shared.module';
import {EnsureAuthenticated} from './shared/services/ensure-authenticated.service';
import {LoginRedirect} from './shared/services/login-redirect.service';
import {MatToolbar, MatToolbarModule} from '@angular/material';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { ProfileComponent } from './components/user/profile/profile.component';
import {UserModule} from './components/user/user.module';
import {GroupModule} from './components/group/group.module';
import {UniversityModule} from './components/university/university.module';
import {FacultyModule} from './components/faculty/faculty.module';
import { RequestsComponent } from './requests/requests.component';


@NgModule({
  declarations: [AppComponent, HeaderComponent, FooterComponent, RequestsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule,
    SignModule,
    IndexModule,
    SharedModule,
    UserModule,
    GroupModule,
    FacultyModule,
    UniversityModule
  ],
  providers: [
    EnsureAuthenticated,
    LoginRedirect,
    JwtService
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
