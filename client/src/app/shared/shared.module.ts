import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {IsNeedHeaderAndFooterDirective} from './directives/is-need-header-and-footer.directive';
import {IsAdminDirective} from './directives/is-admin.directive';
import {IsNotLoginedInDirectiveDirective} from './directives/is-not-logined-in-directive.directive';
import {SearchPipe} from './pipes/search.pipe';
import {IsLoginedInDirective} from './directives/is-logined-in.directive';
import {SubjectComponent} from './subject/subject/subject.component';
import {SubjectPickerComponent} from './subject/subject-picker/subject-picker.component';
import {SubjectService} from './services/subject.service';
import {HttpClientModule} from '@angular/common/http';
import {RouterModule} from '@angular/router';
import {FormsModule} from '@angular/forms';
import {UserSubjectComponent} from './subject/user-subject/user-subject.component';
import {ErrorsComponent} from './errors/errors.component';
import {SubjectListComponent} from './subject/subject-list/subject-list.component';
import {FileUploadService} from './services/fileUpload.service';
import {GroupComponent} from '../components/group/group/group.component';
import {FacultyComponent} from '../components/faculty/faculty/faculty.component';


@NgModule({
  imports: [
    CommonModule, HttpClientModule, RouterModule, FormsModule
  ],
  declarations: [FacultyComponent, UserSubjectComponent, GroupComponent, IsAdminDirective, IsLoginedInDirective, IsNeedHeaderAndFooterDirective, IsNotLoginedInDirectiveDirective, SearchPipe, SubjectComponent, SubjectPickerComponent, ErrorsComponent, SubjectListComponent],
  exports: [
    FacultyComponent,
    SubjectComponent, SubjectPickerComponent, UserSubjectComponent, IsAdminDirective, IsLoginedInDirective
    , IsNeedHeaderAndFooterDirective, IsNotLoginedInDirectiveDirective, SearchPipe,
    ErrorsComponent, SubjectListComponent, GroupComponent
  ],
  providers: [SubjectService, HttpClientModule, SubjectService]
})
export class SharedModule {
}
