import {RouterModule, Routes} from '@angular/router';
import {GroupListComponent} from '../group/group-list/group-list.component';
import {NgModule} from '@angular/core';
import {GroupProfileComponent} from '../group/group-profile/group-profile.component';
import {FacultyListComponent} from './faculty-list/faculty-list.component';
import {FacultyProfileComponent} from './faculty-profile/faculty-profile.component';

const routes: Routes = [{path: 'faculties', component: FacultyListComponent},
  {path: 'faculties/:id', component: FacultyProfileComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class FacultyRoutingModule {
}
