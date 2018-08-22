package dev.chermenin.dao.dto.subject;

import dev.chermenin.model.impl.Subject;
import dev.chermenin.dao.dto.Dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * Created by Ancarian on 25.02.2018.
 */

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SubjectScoreDto implements Dto {
    private Subject subject;
    private int score;

    public SubjectScoreDto(Map.Entry<Subject, Integer> subjectIntegerEntry) {
        this.subject = subjectIntegerEntry.getKey();
        this.score = subjectIntegerEntry.getValue();
    }
}
