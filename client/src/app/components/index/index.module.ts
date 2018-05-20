import {NgModule} from '@angular/core';

import {IndexRoutingModule} from './index-routing.module';
import {IndexComponent} from './index.component';
import {CommonModule} from '@angular/common';


@NgModule({
  imports: [
    CommonModule,
    IndexRoutingModule
  ],
  declarations: [IndexComponent],
})
export class IndexModule {
}
