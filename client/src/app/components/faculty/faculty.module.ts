import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FacultyComponent } from './faculty/faculty.component';
import { FacultyListComponent } from './faculty-list/faculty-list.component';
import { FacultyProfileComponent } from './faculty-profile/faculty-profile.component';
import {FacultyRoutingModule} from './faculty-routing.module';
import {SharedModule} from '../../shared/shared.module';
import {FacultyService} from '../../shared/services/faculty.service';
import {GroupModule} from '../group/group.module';

@NgModule({
  imports: [
    CommonModule, FacultyRoutingModule, SharedModule
  ],
  declarations: [ FacultyListComponent, FacultyProfileComponent],

  providers: [FacultyService]
})
export class FacultyModule { }
