import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {UserSubject} from '../../models/model';

@Component({
  selector: 'app-user-subject',
  templateUrl: './user-subject.component.html',
  styleUrls: ['./user-subject.component.css']
})
export class UserSubjectComponent implements OnInit {

  @Input() subject: UserSubject;
  @Output() onRemoved = new EventEmitter<UserSubject>();

  constructor() {
  }

  ngOnInit() {
  }

  remove() {
    this.onRemoved.emit(this.subject);
  }
}
