package dev5.chermenin.service.dto.impl.credentials;

import dev5.chermenin.service.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

@Getter
@Setter
@NoArgsConstructor

public class CredentialEmailDto extends Dto {
    @Email(regexp = "[a-zA-Z0-9_]{6,20}+@[a-z]+\\.[a-z]{2,6}")
    String email;
    @Email(regexp = "[a-zA-Z0-9_]{6,20}+@[a-z]+\\.[a-z]{2,6}")
    String oldEmail;

    public CredentialEmailDto(Long id, String oldEmail, String email) {
        setId(id);
        this.email = email;
        this.oldEmail = oldEmail;
    }
}
