import {Directive, ElementRef, OnInit} from '@angular/core';
import {JwtService} from '../services/jwt.service';

@Directive({
  selector: '[appIsAdmin]'
})
export class IsAdminDirective implements OnInit {

  constructor(private element: ElementRef, private jwtService: JwtService) {
  }

  ngOnInit(): void {
    if (!this.jwtService.isAdmin()) {
      this.element.nativeElement.style.display = 'none';
    } else {
      this.element.nativeElement.style.display = 'block';
    }
  }
}
