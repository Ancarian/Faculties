package dev.chermenin.dao.dto.credentials;

import dev.chermenin.dao.dto.Dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Value;
import org.hibernate.validator.constraints.Email;

@Value
public class CredentialEmailDto implements Dto {
    @Email(regexp = "[a-zA-Z0-9_]{6,20}+@[a-z]+\\.[a-z]{2,6}")
    String email;
    @Email(regexp = "[a-zA-Z0-9_]{6,20}+@[a-z]+\\.[a-z]{2,6}")
    String oldEmail;
    private Long id;

    public CredentialEmailDto(Long id, String oldEmail, String email) {
        this.id = id;
        this.email = email;
        this.oldEmail = oldEmail;
    }
}
