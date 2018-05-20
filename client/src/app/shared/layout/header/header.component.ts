import {Component, OnInit} from '@angular/core';
import {JwtService} from '../../services/jwt.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private jwtService: JwtService) {
  }

  ngOnInit() {

  }

  logout() {
    this.jwtService.destroyToken();
  }
}
