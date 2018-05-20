import {Component, Input, OnInit} from '@angular/core';
import {Faculty} from '../../../shared/models/model';

@Component({
  selector: 'app-faculty',
  templateUrl: './faculty.component.html',
  styleUrls: ['./faculty.component.css']
})
export class FacultyComponent implements OnInit {

  @Input() faculty: Faculty;
  constructor() { }

  ngOnInit() {
  }

}
