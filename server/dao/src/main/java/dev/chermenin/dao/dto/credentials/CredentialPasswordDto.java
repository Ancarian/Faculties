package dev.chermenin.dao.dto.credentials;

import dev.chermenin.dao.dto.Dto;
import lombok.Value;

import javax.validation.constraints.Pattern;

@Value
public class CredentialPasswordDto implements Dto {
    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    String password;
    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    String oldPassword;
    private Long id;

    public CredentialPasswordDto(Long id, String oldPassword, String password) {
        this.id = id;
        this.password = password;
        this.oldPassword = oldPassword;
    }
}
