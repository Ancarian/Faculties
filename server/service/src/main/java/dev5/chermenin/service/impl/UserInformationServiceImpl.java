package dev5.chermenin.service.impl;

import dev5.chermenin.dao.repository.UserInformationRepository;
import dev5.chermenin.model.entity.impl.UserInformation;
import dev5.chermenin.service.api.UserInformationService;
import dev5.chermenin.service.dto.impl.credentials.CredentialEmailDto;
import dev5.chermenin.service.dto.impl.credentials.CredentialPasswordDto;
import dev5.chermenin.service.dto.impl.user.UserInformationDto;
import dev5.chermenin.service.exceptions.ConflictException;
import dev5.chermenin.service.exceptions.ExistsException;
import dev5.chermenin.service.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Created by Ancarian on 10.10.2017.
 */
@Service
@RequiredArgsConstructor
public class UserInformationServiceImpl implements UserInformationService {
    private final UserInformationRepository userInformationRepository;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(UserInformationServiceImpl.class);

    @Override
    public UserInformationDto findById(Long id) {
        UserInformation userInformation = userInformationRepository.findOne(id);
        if (userInformation == null) {
            throw new NotFoundException("User Info not found");
        }
        return new UserInformationDto(userInformation);
    }

    @Override
    public UserInformationDto findByNickname(String nickname) {
        UserInformation userInformation = userInformationRepository.findByNickname(nickname);
        if (userInformation == null) {
            throw new NotFoundException("User Info not found");
        }
        return new UserInformationDto(userInformation);
    }

    @Override
    public UserInformationDto findByEmail(String email) {
        UserInformation userInformation = userInformationRepository.findByEmail(email);
        if (userInformation == null) {
            throw new NotFoundException("User Info not found");
        }
        return new UserInformationDto(userInformation);
    }

    @Transactional
    @Override
    public void changePassword(CredentialPasswordDto credentialPasswordDto) {
        UserInformation userInformation = userInformationRepository.findOne(credentialPasswordDto.getId());
        if (!Objects.equals(userInformation.getPassword(), credentialPasswordDto.getOldPassword())) {
            throw new ConflictException("user's information old password is invalid");
        }
        userInformation.setPassword(passwordEncoder.encode(credentialPasswordDto.getPassword()));
    }

    @Transactional
    @Override
    public void changeEmail(CredentialEmailDto credentialEmailDto) {
        if (userInformationRepository.findByEmail(credentialEmailDto.getEmail()) != null) {
            throw new ExistsException("user's information with this email already exists");
        }
        UserInformation userInformation = userInformationRepository.findOne(credentialEmailDto.getId());
        if (!Objects.equals(userInformation.getEmail(), credentialEmailDto.getOldEmail())) {
            throw new ConflictException("user's information old email is invalid");
        }
        userInformation.setEmail(credentialEmailDto.getEmail());
    }

    @Transactional
    @Override

    public UserInformation findUserInfoById(Long id) {

        UserInformation userInformation = userInformationRepository.findOne(id);
        System.out.println(userInformation.getRoles());
        return userInformation;
    }
}
