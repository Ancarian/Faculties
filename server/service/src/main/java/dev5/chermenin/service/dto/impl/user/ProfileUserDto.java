package dev5.chermenin.service.dto.impl.user;

import dev5.chermenin.model.entity.impl.User;
import dev5.chermenin.service.dto.Dto;
import dev5.chermenin.service.dto.impl.subject.SubjectScoreDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class ProfileUserDto extends Dto {
    private Long groupId;

    private String photoUrl;

    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    private String name;

    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    private String lastname;

    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    private String patronymic;

    @Valid
    private UserInformationDto info;

    private Set<SubjectScoreDto> marks;

    public ProfileUserDto(User user) {
        this.setId(user.getId());
        if (user.getGroup() != null){
            this.groupId = user.getGroup().getId();
        }
        this.photoUrl = user.getPhotoUrl();
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.patronymic = user.getPatronymic();

        if (user.getSubjects() != null) {
            marks = user.getSubjects().entrySet().stream()
                    .map(SubjectScoreDto::new)
                    .collect(Collectors.toSet());
        }

        this.info = new UserInformationDto(user.getInfo());
    }

    public ProfileUserDto() {
    }
}

