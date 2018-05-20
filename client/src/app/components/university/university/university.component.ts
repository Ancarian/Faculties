import {Component, Input, OnInit} from '@angular/core';
import {University} from '../../../shared/models/model';

@Component({
  selector: 'app-university',
  templateUrl: './university.component.html',
  styleUrls: ['./university.component.css']
})
export class UniversityComponent implements OnInit {


  @Input() university: University;
  constructor() { }

  ngOnInit() {
  }

}
