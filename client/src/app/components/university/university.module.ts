import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UniversityComponent } from './university/university.component';
import { UniversityListComponent } from './university-list/university-list.component';
import { UniversityProfileComponent } from './university-profile/university-profile.component';
import {SharedModule} from '../../shared/shared.module';
import {UniversityRoutingModule} from './university-routing.module';
import {UniversityService} from '../../shared/services/university.service';

@NgModule({
  imports: [
    CommonModule, SharedModule, UniversityRoutingModule
  ],
  declarations: [UniversityComponent, UniversityListComponent, UniversityProfileComponent],
  providers: [UniversityService]
})
export class UniversityModule { }
