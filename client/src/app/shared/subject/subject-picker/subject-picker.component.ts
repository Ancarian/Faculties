import {Component, Input, OnInit} from '@angular/core';
import {SubjectService} from '../../services/subject.service';
import {Subject, UserSubject} from '../../models/model';

@Component({
  selector: 'app-subject-picker',
  templateUrl: './subject-picker.component.html',
  styleUrls: ['./subject-picker.component.css']
})
export class SubjectPickerComponent implements OnInit {
  subjects: Subject[] = [];
  @Input() userSubjects: UserSubject[];
  searchStr: String;

  constructor(private subjectService: SubjectService) {
  }

  ngOnInit() {
    this.findAll();
  }

  onUserSubjectsChange(subject: Subject) {
    const userSubject = new UserSubject();
    userSubject.id = subject.id;
    userSubject.subject = subject.subject;
    userSubject.score = 0;
    this.userSubjects.push(userSubject);
    this.searchStr = '';
    // this.userSubjectsChange.emit(this.userSubjects);
  }

  findAll() {
    this.subjectService.findAll().subscribe(
      res => {
        this.subjects = res;
        console.log(this.subjects);
      }, (error) => {
        console.log(error);
      });
  }

  removeFromUserSubjects(userSubject: UserSubject) {
    const index = this.userSubjects.indexOf(userSubject, 0);
    if (index > -1) {
      this.userSubjects.splice(index, 1);
    }
  }
}
