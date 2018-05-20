import {Component, Input, OnInit} from '@angular/core';
import {SubjectService} from '../../services/subject.service';
import {Subject} from '../../models/model';


@Component({
  selector: 'app-subject-list',
  templateUrl: './subject-list.component.html',
  styleUrls: ['./subject-list.component.css']
})
export class SubjectListComponent implements OnInit {
  @Input() inventoryIds: number[];
  @Input() subjs: Subject[];
  subjects: Subject[];

  constructor(private subjectService: SubjectService) {
  }

  ngOnInit() {
    this.initSubjects();
  }

  private initSubjects() {
    if (this.inventoryIds == null){
      this.subjects = this.subjs;
    } else {
      this.subjectService.getByInvendatoryIds(this.inventoryIds).subscribe(res => {
        this.subjects = res;
      }, error1 => {
        console.log(error1);
      });
    }

  }

}
