import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { GroupComponent } from './group/group.component';
import { GroupListComponent } from './group-list/group-list.component';
import {GroupRoutingModule} from './group-routing.module';
import {SharedModule} from '../../shared/shared.module';
import {GroupService} from '../../shared/services/group.service';
import { GroupProfileComponent } from './group-profile/group-profile.component';

@NgModule({
  imports: [
    CommonModule, GroupRoutingModule, SharedModule
  ],
  declarations: [GroupListComponent, GroupProfileComponent],
  providers: [GroupService]
})
export class GroupModule { }
