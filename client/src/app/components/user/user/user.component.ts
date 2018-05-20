import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {UserService} from '../../../shared/services/user.service';
import {User} from '../../../shared/models/model';
import {RequestService} from '../../../shared/services/request.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  @Input() user: User;
  constructor(private requestService: RequestService) { }

  @Output() removedId = new EventEmitter<User>();
  ngOnInit() {
  }


  confirm() {
    this.requestService.confirm(this.user.id).subscribe(res => {

    }, error1 => {
      console.log(error1);
    });
    this.removedId.emit(this.user);
  }

  cancel() {
    this.requestService.cancel(this.user.id).subscribe();
    this.removedId.emit(this.user);
  }

}
