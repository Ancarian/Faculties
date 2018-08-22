package dev.chermenin.dao.dto.user;

import dev.chermenin.dao.dto.Dto;
import dev.chermenin.model.impl.User;
import dev.chermenin.model.impl.enums.Sex;
import lombok.Value;

@Value
public class UserDto implements Dto {
    private Long id;
    private String name;
    private String lastname;
    private String patronymic;
    private String photoUrl;
    private boolean verified;
    private String nickname;
    private Sex sex;
    private String adress;

    public UserDto(Long id, String name, String lastname, String patronymic, String photoUrl, boolean verified, String nickname, Sex sex, String adress) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.patronymic = patronymic;
        this.photoUrl = photoUrl;
        this.verified = verified;
        this.nickname = nickname;
        this.sex = sex;
        this.adress = adress;
    }


    public UserDto(User user) {
        this.id = (user.getId());
        this.name = user.getName();
        this.lastname = user.getLastname();
        this.patronymic = user.getPatronymic();
        this.photoUrl = user.getPhotoUrl();
        this.verified = user.isVerified();
        this.nickname = user.getNickname();
        this.sex = user.getSex();
        this.adress = user.getAdress();
    }
}
