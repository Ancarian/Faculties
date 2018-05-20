package dev5.chermenin.service.dto.impl.subject;

import dev5.chermenin.model.entity.impl.Subject;
import dev5.chermenin.service.dto.Dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.Map;

/**
 * Created by Ancarian on 25.02.2018.
 */

@Getter
@Setter
@NoArgsConstructor
public class SubjectScoreDto extends Dto {
    @Pattern(regexp = "[a-zA-Z0-9_]{3,20}+")
    private String subject;
    private int score;

    public SubjectScoreDto(Map.Entry<Subject, Integer> subjectIntegerEntry) {
        this.setId(subjectIntegerEntry.getKey().getId());
        this.subject = subjectIntegerEntry.getKey().getSubject();
        this.score = subjectIntegerEntry.getValue();
    }

}
