import {Component, Input, OnInit} from '@angular/core';
import {Group} from '../../../shared/models/model';

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {
  @Input() group: Group;
  constructor() { }

  ngOnInit() {
  }

}
