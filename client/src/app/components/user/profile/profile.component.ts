import {Component, OnInit} from '@angular/core';
import {AuthService} from '../../../shared/services/auth.service';
import {User} from '../../../shared/models/model';
import {FileUploadService} from '../../../shared/services/fileUpload.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  user: User;
  isShowPicker = false;

  constructor(private authService: AuthService, private fileUploadService: FileUploadService) {
  }

  ngOnInit() {
    this.initUser();
  }

  changeChoice(value: boolean) {
    if (value && this.isShowPicker || !value && !this.isShowPicker) {
      this.isShowPicker = !this.isShowPicker;
    }
  }

  initUser() {
    this.authService.me().subscribe(
      res => {
        this.user = res;
      }, error => {
        console.log(error);
      });
  }

  handleFileInput(files: FileList) {
    this.fileUploadService.postFile(files.item(0)).subscribe(data => {
      this.initUser();
    }, error => {
      console.log(error);
    });
  }
}
