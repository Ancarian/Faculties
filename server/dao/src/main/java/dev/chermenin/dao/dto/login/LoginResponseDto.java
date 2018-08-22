package dev.chermenin.dao.dto.login;

import dev.chermenin.dao.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto implements Dto {
    private String token;
}

