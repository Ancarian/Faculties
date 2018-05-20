package dev5.chermenin.service.dto.impl.user;

import dev5.chermenin.service.dto.Dto;
import dev5.chermenin.service.dto.impl.subject.SubjectScoreDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Pattern;
import java.util.List;

@Getter
@Setter
@ToString
public class RegisterDto extends Dto {

    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    private String name;

    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    private String lastname;

    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    private String patronymic;

    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    private String nickname;

    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    private String password;

    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+@[a-z]+\\.[a-z]{2,6}")
    private String email;

    private List<SubjectScoreDto> userSubjects;

}
