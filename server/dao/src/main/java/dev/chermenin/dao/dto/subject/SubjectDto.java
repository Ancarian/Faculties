package dev.chermenin.dao.dto.subject;

import dev.chermenin.dao.dto.Dto;
import dev.chermenin.model.impl.Subject;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class SubjectDto implements Dto {
    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    private String subject;
    private Long id;

    public SubjectDto(Subject subject) {
        this.id = subject.getId();
        this.subject = subject.getSubject();
    }
}

