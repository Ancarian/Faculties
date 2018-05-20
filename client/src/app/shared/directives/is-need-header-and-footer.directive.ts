import {Directive, ElementRef, OnInit, Renderer2} from '@angular/core';
import {Router} from '@angular/router';

@Directive({
  selector: '[appIsNeedHeaderAndFooter]'
})
export class IsNeedHeaderAndFooterDirective implements OnInit {
  constructor(private element: ElementRef, private router: Router, private render: Renderer2) {
  }

  ngOnInit(): void {
    this.router.events.subscribe((url: any) => {
        if (!['/'].includes(url.url)) {
          this.render.setStyle(this.element.nativeElement, 'display', 'none');
        } else {
          this.render.removeStyle(this.element.nativeElement, 'display');
        }
      }
    );
  }
}
