package dev.chermenin.dao.dto.credentials;

import dev.chermenin.dao.dto.Dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class ResetPasswordDto implements Dto {

    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    String password;
}
