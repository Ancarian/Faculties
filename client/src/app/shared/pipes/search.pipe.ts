import {Pipe, PipeTransform} from '@angular/core';
import {Subject} from '../models/model';

@Pipe({
  name: 'search'
})
export class SearchPipe implements PipeTransform {

  transform(subjects: Subject[], filter: string, existsSubjects: Subject[]): Array<any> {
    if (filter == '') {
      return [];
    }
    return subjects.filter(subject => {
      return subject.subject.includes(filter) && existsSubjects.filter(subj => {
        return subj.subject.includes(filter);
      }).length == 0;
    });
  }
}
