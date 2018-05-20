import {Directive, ElementRef, OnInit} from '@angular/core';
import {JwtService} from '../services/jwt.service';

@Directive({
  selector: '[appIsNotLoginedInDirective]'
})
export class IsNotLoginedInDirectiveDirective implements OnInit{

  constructor(private element: ElementRef, private jwtService: JwtService) {
  }

  ngOnInit(): void {
    if (this.jwtService.isLoggedIn()) {
      this.element.nativeElement.style.display = 'none';
    } else {
      this.element.nativeElement.style.display = 'block';
    }
  }

}
