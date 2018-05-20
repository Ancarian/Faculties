package dev5.chermenin.service.dto.impl.credentials;

import dev5.chermenin.service.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class CredentialPasswordDto extends Dto {
    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    String password;
    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    String oldPassword;

    public CredentialPasswordDto(Long id, String oldPassword, String password) {
        setId(id);
        this.password = password;
        this.oldPassword = oldPassword;
    }
}
