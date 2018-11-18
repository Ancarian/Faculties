# Faculties

University admission system
<br/>
[![Build Status](https://travis-ci.org/Ancarian/Faculties.svg?branch=master)](https://travis-ci.org/Ancarian/Faculties) <a href="https://codeclimate.com/github/Ancarian/Faculties/maintainability"><img src="https://api.codeclimate.com/v1/badges/48ce9aed8e3b6ae4716f/maintainability" /></a>
## Server

### Stack of technologies on the server side

* Spring Boot
* Maven
* Spring Security
* Spring MVC REST
* Spring Data Jpa
* Hibernate ORM
* Swagger API
 
### How to run

* Clone this repository 
* Run ```run.bat``` script in scripts folder
* Navigate to [http://localhost:9000/swagger-ui.html](http://localhost:9000/swagger-ui.html)
* Input in /auth/login response 'nickname_1' and 'password_1' and copy jwt token.
 Jwt token looks like ```eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOjEsImV4cCI6MTUxOTU5NjE1Niwicm9sZXMiOlsiQURNSU4iXX0.p7pCjbyf-atDzApUOTU4wuYoXrsWVrRgjAmg6_NdOo4```
* click the authorize button and paste into api_key field ```bearer *your jwt token*```
* ???
* PROFIT

### Swagger ui
![alt text](https://i.imgur.com/4RfORLZ.png)
![alt text](https://i.imgur.com/YnGZjZa.png)
### Other
* You can use ```generator_script_H2.py``` for generate import.sql

## Client


