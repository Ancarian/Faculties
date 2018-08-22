package dev.chermenin.service.api.impl;

import dev.chermenin.dao.SubjectRepository;
import dev.chermenin.dao.UserRepository;
import dev.chermenin.model.impl.Subject;
import dev.chermenin.model.impl.User;
import dev.chermenin.model.impl.adress.City;
import dev.chermenin.model.impl.enums.Roles;
import dev.chermenin.service.api.UserService;
import dev.chermenin.dao.dto.create.UserRegistrationDto;
import dev.chermenin.dao.dto.user.UserDto;
import dev.chermenin.dao.dto.user.UserProfileDto;
import dev.chermenin.service.exception.ConflictException;
import dev.chermenin.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SubjectRepository subjectRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserProfileDto findById(long id) {
        return new UserProfileDto(userRepository.findById(id).orElseThrow(
                () -> new NotFoundException(String.format("user with id %d not exists", id))));
    }

    @Override
    public UserProfileDto findByNickname(String nickname) {
        return new UserProfileDto(userRepository.findUserByNickname(nickname).orElseThrow(
                () -> new NotFoundException(String.format("user with nickname %s not exists", nickname))));
    }

    @Override
    public UserProfileDto findByEmail(String email) {
        return new UserProfileDto(userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException(String.format("user with email %s not exists", email))));
    }

    @Override
    public UserProfileDto save(UserRegistrationDto userRegistrationDto) {
        if (userRepository.existsByEmailOrNickname(userRegistrationDto.getEmail(),
                userRegistrationDto.getNickname())) {
            throw new ConflictException("user with this credentials already exists");
        }

        User user = new User();
        user.setName(userRegistrationDto.getName());
        user.setLastname(userRegistrationDto.getName());
        user.setPatronymic(userRegistrationDto.getPatronymic());
        user.setNickname(userRegistrationDto.getNickname());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setSex(userRegistrationDto.getSex());
        user.setRole(Roles.USER);
        user.setPhotoUrl(userRegistrationDto.getPhotoUrl());
        user.setCity(new City());
        user.getCity().setId(userRegistrationDto.getCityId());
        user.setAdress(userRegistrationDto.getAdress());

        Map<Subject, Integer> subjects = new HashMap<>();
        userRegistrationDto.getSubjects().forEach(subject -> {
            subjects.put(subject.getSubject(), subject.getScore());
        });

        user.setSubjects(subjects);
        return new UserProfileDto(userRepository.save(user));
    }

    @Override
    public User saveUser(UserRegistrationDto userRegistrationDto) {
        if (userRepository.existsByEmailOrNickname(userRegistrationDto.getEmail(),
                userRegistrationDto.getNickname())) {
            throw new ConflictException("error.user.alreadyExists");
        }

        User user = new User();
        user.setName(userRegistrationDto.getName());
        user.setLastname(userRegistrationDto.getName());
        user.setPatronymic(userRegistrationDto.getPatronymic());
        user.setNickname(userRegistrationDto.getNickname());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setSex(userRegistrationDto.getSex());
        user.setRole(Roles.USER);
        user.setPhotoUrl(userRegistrationDto.getPhotoUrl());
        user.setCity(new City());
        user.getCity().setId(userRegistrationDto.getCityId());
        user.setAdress(userRegistrationDto.getAdress());

        Map<Subject, Integer> subjects = new HashMap<>();
        userRegistrationDto.getSubjects().forEach(subject -> {
            subjects.put(subject.getSubject(), subject.getScore());
        });

        user.setSubjects(subjects);
        return userRepository.save(user);
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(UserDto::new);
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable, Specification<User> specification) {
        return userRepository.findAll(specification, pageable).map(UserDto::new);
    }

    @Override
    public Page<UserDto> getRequests(Long id, Pageable pageable) {
        return userRepository.findAllByGroupIdAndVerifiedFalse(id, pageable).map(UserDto::new);
    }

    @Override
    public void remove(long id) {
        if (!userRepository.existsById(id)) {
            throw new NotFoundException(String.format("user with id %d not exists", id));
        }
        userRepository.deleteById(id);
    }
}
