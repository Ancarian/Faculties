import { Component, OnInit } from '@angular/core';
import {Faculty} from '../../../shared/models/model';
import {ActivatedRoute, Router} from '@angular/router';
import {GroupService} from '../../../shared/services/group.service';
import {FacultyService} from '../../../shared/services/faculty.service';


@Component({
  selector: 'app-faculty-profile',
  templateUrl: './faculty-profile.component.html',
  styleUrls: ['./faculty-profile.component.css']
})
export class FacultyProfileComponent implements OnInit {
  faculty: Faculty;

  constructor(private route: ActivatedRoute, private router: Router, private facultyService: FacultyService) {
  }

  ngOnInit() {
    this.initFaculty();
  }

  private initFaculty() {
    this.route.params.subscribe(params => {
      this.facultyService.findById(params.id).subscribe(faculty => {
        this.faculty = faculty;
      });
    });
  }
}
