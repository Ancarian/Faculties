import { Component, OnInit } from '@angular/core';
import {Group, University} from '../../../shared/models/model';
import {GroupService} from '../../../shared/services/group.service';
import {UniversityService} from '../../../shared/services/university.service';

@Component({
  selector: 'app-university-list',
  templateUrl: './university-list.component.html',
  styleUrls: ['./university-list.component.css']
})
export class UniversityListComponent implements OnInit {

  universities: University[];

  constructor(private universityService: UniversityService) {
  }

  ngOnInit() {
    this.initGroups();
  }

  json() {
    return JSON.stringify(this.universities);
  }

  private initGroups() {
    this.universityService.findAll().subscribe(res => {
      this.universities = res;
    }, error1 => {
      console.log(error1);
    });
  }

}
