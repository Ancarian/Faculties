import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {GroupComponent} from './group/group.component';
import {GroupListComponent} from './group-list/group-list.component';
import {GroupProfileComponent} from './group-profile/group-profile.component';

const routes: Routes = [{path: 'groups', component: GroupListComponent},
  {path: 'groups/:id', component: GroupProfileComponent}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class GroupRoutingModule {
}
