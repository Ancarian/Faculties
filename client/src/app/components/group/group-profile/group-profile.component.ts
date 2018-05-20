import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Group} from '../../../shared/models/model';
import {GroupService} from '../../../shared/services/group.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {delay} from 'rxjs/operator/delay';
import {UserService} from '../../../shared/services/user.service';

@Component({
  selector: 'app-group-profile',
  templateUrl: './group-profile.component.html',
  styleUrls: ['./group-profile.component.css']
})
export class GroupProfileComponent implements OnInit {
  group: Group;
  isShowPicker = false;
  form: FormGroup;
  error: string;

  constructor(private route: ActivatedRoute, private router: Router, private groupService: GroupService, private userService: UserService) {
  }

  ngOnInit() {
    this.initGroup();
  }

  private initForm() {
    this.form = new FormGroup({
      'information': new FormControl(this.group.information, [
        Validators.required,
        Validators.minLength(4),
        Validators.maxLength(20),
      ]),
      'limit': new FormControl(this.group.limit,
        [Validators.required,
          Validators.minLength(4),
          Validators.maxLength(20)]),
      'facultyId': new FormControl(this.group.facultyComponentDto.id,
        [Validators.required,
          Validators.min(1)]),
      'qualify':
        new FormControl(this.group.qualify,
          [Validators.required,
            Validators.minLength(4),
            Validators.maxLength(20)]),
      'issueDate':
        new FormControl(this.group.issueDate,
          [Validators.required]),
      'validTill':
        new FormControl(this.group.validTill,
          [Validators.required])
    });
  }

  private initGroup() {
    this.route.params.subscribe(params => {
      this.groupService.findById(params.id).subscribe(group => {
        this.group = group;
        this.initForm();
        console.log(this.group);
      }, error => {
        console.log(error);
      });
    });
  }


  update(): void {

  }

  remove(): void {
    this.groupService.deleteById(2).subscribe(res => {
      this.router.navigate(['faculties/']);
      console.log(this.group);
    }, error => {
      this.router.navigate(['faculties/']);
      console.log(error);
    });
  }

  changeChoice(value: boolean) {
    if (value && this.isShowPicker || !value && !this.isShowPicker) {
      this.isShowPicker = !this.isShowPicker;
    }
  }

  sendRequest() {
    this.userService.sendRequest(this.group.id).subscribe(
      res => {

      }, error1 => {
        this.error = 'koko';
        console.log(error1);
      }
    );
  }
}
