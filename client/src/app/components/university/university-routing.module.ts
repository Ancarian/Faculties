import {RouterModule, Routes} from '@angular/router';
import {NgModule} from '@angular/core';
import {UniversityListComponent} from './university-list/university-list.component';
import {UniversityProfileComponent} from './university-profile/university-profile.component';

const routes: Routes = [{path: 'universities', component: UniversityListComponent},
  {path: 'universities/:id', component: UniversityProfileComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class UniversityRoutingModule {
}
