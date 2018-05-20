package dev5.chermenin.service.dto.impl.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev5.chermenin.model.entity.impl.UserInformation;
import dev5.chermenin.service.dto.Dto;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserInformationDto extends Dto {

    @JsonIgnore
    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    private String password;

    @Email(regexp = "[a-zA-Z0-9_]{6,20}+@[a-z]+\\.[a-z]{2,6}")
    private String email;

    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    private String nickname;

    public UserInformationDto(UserInformation userInformation) {
        setId(userInformation.getId());
        password = userInformation.getPassword();
        email = userInformation.getEmail();
        nickname = userInformation.getNickname();
    }

    public UserInformationDto() {

    }
}

