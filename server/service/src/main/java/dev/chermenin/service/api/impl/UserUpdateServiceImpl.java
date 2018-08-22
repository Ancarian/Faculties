package dev.chermenin.service.api.impl;

import dev.chermenin.dao.GroupRepository;
import dev.chermenin.dao.UserRepository;
import dev.chermenin.model.impl.Group;
import dev.chermenin.model.impl.User;
import dev.chermenin.model.impl.adress.City;
import dev.chermenin.service.api.UserUpdateService;
import dev.chermenin.dao.dto.create.UserRegistrationDto;
import dev.chermenin.dao.dto.credentials.CredentialEmailDto;
import dev.chermenin.dao.dto.credentials.CredentialPasswordDto;
import dev.chermenin.service.exception.BadRequestException;
import dev.chermenin.service.exception.ConflictException;
import dev.chermenin.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserUpdateServiceImpl implements UserUpdateService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    @Transactional
    @Override
    public void update(UserRegistrationDto userRegistrationDto) {
        if (userRegistrationDto.getId() == null) {
            throw new BadRequestException("user id is null");
        }
        User user = userRepository.findById(userRegistrationDto.getId()).orElseThrow(
                () -> new NotFoundException("user not exists")
        );
        user.setName(userRegistrationDto.getName());
        user.setLastname(userRegistrationDto.getLastname());
        user.setPatronymic(userRegistrationDto.getPatronymic());
        user.setSex(userRegistrationDto.getSex());
        user.setCity(new City());
        user.getCity().setId(userRegistrationDto.getCityId());
        user.setAdress(userRegistrationDto.getAdress());
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void selectGroup(long userId, long groupId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("not exists")
        );
        Group group = groupRepository.findByIdAndGroupInformationSubjects(groupId,
                user.getSubjects().keySet()).orElseThrow(
                () -> new NotFoundException("dsh")
        );
        user.setGroup(group);
        user.setVerified(false);
    }

    @Transactional
    @Override
    public void changeStateOfRequest(long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("not exists")
        );
        if (user.getGroup() == null) {
            throw new ConflictException("group not selected");
        }
        user.setVerified(!user.isVerified());
    }

    @Transactional
    @Override
    public void setPhotoToUser(Long userId, String photoUrl) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("not exists")
        );
        user.setPhotoUrl(photoUrl);
    }

    @Transactional
    @Override
    public void changePassword(CredentialPasswordDto credentialsDto) {
        User user = userRepository.findById(credentialsDto.getId()).orElseThrow(
                () -> new NotFoundException("not exists")
        );
        if (!user.getPassword().equals(credentialsDto.getOldPassword())) {
            throw new ConflictException("incorrect old password");
        }
        user.setPassword(credentialsDto.getPassword());
    }

    @Transactional
    @Override
    public void changeEmail(CredentialEmailDto credentialsDto) {
        if (userRepository.existsByEmailAndIdNot(credentialsDto.getEmail(), credentialsDto.getId())) {
            throw new ConflictException("user with this email already exists");
        }
        User user = userRepository.findById(credentialsDto.getId()).orElseThrow(
                () -> new NotFoundException("not exists")
        );
        user.setEmail(credentialsDto.getEmail());
    }

    @Override
    public void enableUser(User user) {
        user.setEnabled(true);
        userRepository.save(user);
    }
}
