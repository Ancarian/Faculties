import {Component, Input, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-errors',
  templateUrl: './errors.component.html',
  styleUrls: ['./errors.component.css']
})
export class ErrorsComponent implements OnInit {
  formErrors: {} = {};
  @Input() form: FormGroup;
  @Input() errorMessages: {};

  ngOnInit(): void {
    this.onChange();
  }

  keys(): Array<string> {
    return Object.keys(this.formErrors);
  }

  private onChange() {
    const controls = this.form.controls;
    Object.keys(controls).forEach(controlName => {
        this.form.get(controlName).valueChanges.subscribe(val => {
          if (this.isControlInvalid(controls[controlName])) {
            console.log(controls[controlName]);
            this.formErrors[controlName] = this.getControlErrorMessage(controls[controlName]);
          } else {
            delete this.formErrors[controlName];
          }
        });
      }
    );
  }

  private isControlInvalid(control: AbstractControl): boolean {
    return control.invalid;
  }

  private getControlErrorMessage(control: AbstractControl) {
    const error = [];

    Object.keys(control.errors).forEach( key => {
      error.push(this.errorMessages[key]);
    });

    return error;
  }
}
