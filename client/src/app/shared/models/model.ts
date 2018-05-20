import {s} from '@angular/core/src/render3';

export class University {
  id: number;
  name: string;
  faculties: Faculty[];
}

export class Faculty {

  id: number;
  name: string;
  information: string;
  universityDto: {
    id: number;
    name: string;
  };
  groups: Group[];

}

export class Group {
  id: number;
  information: string;
  limit: number;
  users: UserSubject[];
  enrollMark: number;
  facultyComponentDto: ComponentFaculty;
  issueDate: Date;
  validTill: Date;
  qualify: string;
  subjects: number[];
}

export class ComponentFaculty {
  id: number;
  name: string;
  information: string;
}

export class Registration {
  id: number;
  name: string;
  lastname: string;
  patronymic: string;
  email: string;
  nickname: string;
  password: string;
  userSubjects: UserSubject[] = [];
}

export class UserInformation {
  id: number;
  nickname: string;

  constructor(id: number, nickname: string) {
    this.id = id;
    this.nickname = nickname;
  }
}

export class Subject {
  id: number;
  subject: string;
}

export class UserSubject {
  id: number;
  subject: string;
  score: number;
}

export class User {
  id: number;
  photoUrl: string;
  lastname: string;
  marks: UserSubject[];
  name: string;
  patronymic: string;
  speciality: string;
  info: UserInformation;
  groupId: number;
}

export class LoginResponse {
  token: string;
}

export class LoginRequest {
  username: string;
  password: string;
}

