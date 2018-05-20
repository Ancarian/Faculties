import {Component, OnInit} from '@angular/core';
import {User} from '../../../shared/models/model';
import {UserService} from '../../../shared/services/user.service';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  users: User[] = [];

  constructor(private userService: UserService) {
  }

  ngOnInit() {
    this.initUsers();
  }

  getjson() {
    return JSON.stringify(this.users);
  }

  private initUsers() {
    this.userService.getRequests().subscribe(res => {
      this.users = res;
      console.log(213);
    }, error => {
      console.log(error);
    });
  }

  remove(user: User){

    this.initUsers();
    // const index = this.users.indexOf(user);
    // this.users.splice(index, index + 1);
  }
}
