package dev.chermenin.service.api;

import dev.chermenin.dao.dto.create.UserRegistrationDto;
import dev.chermenin.dao.dto.credentials.CredentialEmailDto;
import dev.chermenin.dao.dto.credentials.CredentialPasswordDto;
import dev.chermenin.model.impl.User;

public interface UserUpdateService {

    void update(UserRegistrationDto userRegistrationDto);

    void selectGroup(long userId, long groupId);

    void changeStateOfRequest(long userId);

    void setPhotoToUser(Long userId, String photoUrl);

    void changePassword(CredentialPasswordDto credentialsDto);

    void changeEmail(CredentialEmailDto credentialsDto);

    void enableUser(User user);
}
