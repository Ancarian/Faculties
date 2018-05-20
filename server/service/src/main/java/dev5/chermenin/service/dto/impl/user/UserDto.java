package dev5.chermenin.service.dto.impl.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev5.chermenin.model.entity.impl.User;
import dev5.chermenin.service.dto.Dto;
import dev5.chermenin.service.dto.impl.subject.SubjectScoreDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UserDto extends Dto {

    private Long groupId;

    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    private String name;

    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    private String lastname;

    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    private String patronymic;

    private Set<SubjectScoreDto> marks;

    @JsonIgnore
    private UserInformationDto userInformationDto;

    public UserDto(User user) {

        this.setId(user.getId());
        if (user.getGroup() != null) {
            this.groupId = user.getGroup().getId();
        }

        this.name = user.getName();
        this.lastname = user.getLastname();
        this.patronymic = user.getPatronymic();
        if (user.getSubjects() != null) {
            marks = user.getSubjects().entrySet().stream()
                    .map(SubjectScoreDto::new)
                    .collect(Collectors.toSet());
        }

    }
}

