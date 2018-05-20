import { Component, OnInit } from '@angular/core';
import {Faculty, Group} from '../../../shared/models/model';
import {GroupService} from '../../../shared/services/group.service';
import {FacultyService} from '../../../shared/services/faculty.service';

@Component({
  selector: 'app-faculty-list',
  templateUrl: './faculty-list.component.html',
  styleUrls: ['./faculty-list.component.css']
})
export class FacultyListComponent implements OnInit {
  faculties: Faculty[];

  constructor(private facultyService: FacultyService) {
  }

  ngOnInit() {
    this.initFaculties();
  }

  json() {
    return JSON.stringify(this.faculties);
  }

  private initFaculties() {
    this.facultyService.findAll().subscribe(res => {
      this.faculties = res;
    }, error1 => {
      console.log(error1);
    });
  }

}
