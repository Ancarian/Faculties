// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

import {HttpHeaders} from '@angular/common/http';

export const environment = {
  production: false,
  baseUrl: 'http://localhost:9000/',
  stringPattern: '[a-zA-Z0-9_]{6,20}+',
  emailPattern: '[a-zA-Z0-9_]{6,20}+@[a-z]+.[a-z]{2,6}',
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${localStorage.getItem('token')}`
  }),

  auth_error_messages: {
    minlength: 'min length is 6',
    maxlength: 'max length is 20',
    email: 'invalid mail',
  }
};
