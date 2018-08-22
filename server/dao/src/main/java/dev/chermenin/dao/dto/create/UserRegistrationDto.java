package dev.chermenin.dao.dto.create;

import dev.chermenin.model.impl.Group;
import dev.chermenin.model.impl.University;
import dev.chermenin.model.impl.User;
import dev.chermenin.model.impl.adress.City;
import dev.chermenin.model.impl.enums.Sex;
import dev.chermenin.dao.dto.Dto;
import dev.chermenin.dao.dto.subject.SubjectScoreDto;
import dev.chermenin.dao.dto.user.UserProfileDto;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UserRegistrationDto implements Dto{
    private Long id;
    private String name;
    private String lastname;
    private String patronymic;
    private List<SubjectScoreDto> subjects;
    private String photoUrl;
    private String nickname;
    private Sex sex;
    private String adress;
    private Long cityId;
    private String email;
    private String password;
}
