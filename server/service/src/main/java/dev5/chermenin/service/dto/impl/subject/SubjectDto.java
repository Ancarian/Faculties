package dev5.chermenin.service.dto.impl.subject;

import dev5.chermenin.model.entity.impl.Subject;
import dev5.chermenin.service.dto.Dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class SubjectDto extends Dto {
    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    private String subject;

    public SubjectDto(Subject subject) {
        this.setId(subject.getId());
        this.subject = subject.getSubject();

    }
}

