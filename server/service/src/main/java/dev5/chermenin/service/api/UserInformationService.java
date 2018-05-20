package dev5.chermenin.service.api;

import dev5.chermenin.model.entity.impl.UserInformation;
import dev5.chermenin.service.dto.impl.credentials.CredentialEmailDto;
import dev5.chermenin.service.dto.impl.credentials.CredentialPasswordDto;
import dev5.chermenin.service.dto.impl.user.UserInformationDto;

public interface UserInformationService {
    UserInformationDto findById(Long id);

    UserInformationDto findByNickname(String nickname);

    UserInformationDto findByEmail(String email);

    UserInformation findUserInfoById(Long id);

    void changePassword(CredentialPasswordDto credentialsDto);

    void changeEmail(CredentialEmailDto credentialsDto);
}

