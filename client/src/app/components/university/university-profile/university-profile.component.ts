import { Component, OnInit } from '@angular/core';
import {FacultyService} from '../../../shared/services/faculty.service';
import {ActivatedRoute, Router} from '@angular/router';
import {Faculty, University} from '../../../shared/models/model';
import {UniversityService} from '../../../shared/services/university.service';

@Component({
  selector: 'app-university-profile',
  templateUrl: './university-profile.component.html',
  styleUrls: ['./university-profile.component.css']
})
export class UniversityProfileComponent implements OnInit {

  university: University;

  constructor(private route: ActivatedRoute, private router: Router, private universityService: UniversityService) {
  }

  ngOnInit() {
    this.initFaculty();
  }

  private initFaculty() {
    this.route.params.subscribe(params => {
      this.universityService.findById(params.id).subscribe(university => {
        this.university = university;
      });
    });
  }
}
