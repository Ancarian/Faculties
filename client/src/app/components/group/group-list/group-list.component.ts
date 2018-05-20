import {Component, OnInit} from '@angular/core';
import {GroupService} from '../../../shared/services/group.service';
import {Group} from '../../../shared/models/model';

@Component({
  selector: 'app-group-list',
  templateUrl: './group-list.component.html',
  styleUrls: ['./group-list.component.css']
})
export class GroupListComponent implements OnInit {
  groups: Group[];

  constructor(private groupService: GroupService) {
  }

  ngOnInit() {
    this.initGroups();
  }

  json() {
    return JSON.stringify(this.groups);
  }

  private initGroups() {
    this.groupService.findAll().subscribe(res => {
      this.groups = res;
    }, error1 => {
      console.log(error1);
    });
  }

}
