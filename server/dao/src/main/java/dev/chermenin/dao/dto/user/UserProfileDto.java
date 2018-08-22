package dev.chermenin.dao.dto.user;

import dev.chermenin.dao.dto.Dto;
import dev.chermenin.dao.dto.subject.SubjectScoreDto;
import dev.chermenin.model.impl.Group;
import dev.chermenin.model.impl.University;
import dev.chermenin.model.impl.User;
import dev.chermenin.model.impl.adress.City;
import dev.chermenin.model.impl.enums.Sex;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserProfileDto implements Dto {
    private String name;
    private String lastname;
    private String patronymic;
    private GroupElementDto group;
    private UniversityElementDto university;
    private List<SubjectScoreDto> subjects;
    private String photoUrl;
    private boolean verified;
    private String nickname;
    private Sex sex;
    private String adress;
    private String cityName;
    private String email;
    private Long id;

    public UserProfileDto(User user) {
        setId(user.getId());
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.patronymic = user.getPatronymic();
        if (user.getGroup() != null) {
            this.group = new GroupElementDto(user.getGroup());
        }
        if (user.getUniversity() != null) {
            this.university = new UniversityElementDto(user.getUniversity());
        }


        subjects = new ArrayList<>();
        user.getSubjects().entrySet().forEach(entry ->
                subjects.add(new SubjectScoreDto(entry)));

        this.photoUrl = user.getPhotoUrl();
        this.verified = user.isVerified();
        this.nickname = user.getNickname();
        this.sex = user.getSex();
        this.email = user.getEmail();
        this.adress = user.getAdress();
        this.cityName = user.getCity().getName();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class GroupElementDto implements Dto {
        private String name;
        private String shortName;
        private Long id;

        public GroupElementDto(Group group) {
            setId(group.getId());
            this.name = group.getGroupInformation().getName();
            this.shortName = group.getGroupInformation().getShortName();
        }

        public GroupElementDto(String name, String shortName, Long id) {
            this.name = name;
            this.shortName = shortName;
            this.id = id;
        }
    }
}
