package dev5.chermenin.service.impl;


import dev5.chermenin.dao.repository.GroupRepository;
import dev5.chermenin.dao.repository.SubjectRepository;
import dev5.chermenin.dao.repository.UserInformationRepository;
import dev5.chermenin.dao.repository.UserRepository;
import dev5.chermenin.model.entity.impl.Group;
import dev5.chermenin.model.entity.impl.Subject;
import dev5.chermenin.model.entity.impl.User;
import dev5.chermenin.model.entity.impl.UserInformation;
import dev5.chermenin.model.entity.impl.enums.Roles;
import dev5.chermenin.service.api.SubjectService;
import dev5.chermenin.service.api.UserService;
import dev5.chermenin.service.dto.impl.user.ProfileUserDto;
import dev5.chermenin.service.dto.impl.user.RegisterDto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import dev5.chermenin.service.exceptions.ConflictException;
import dev5.chermenin.service.exceptions.ExistsException;
import dev5.chermenin.service.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ancarian on 10.10.2017.
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final UserInformationRepository userInformationRepository;

    private final SubjectRepository subjectRepository;

    private final SubjectService subjectService;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDto findById(long id) {
        User user = userRepository.findOne(id);
        return new UserDto(user);
    }

    @Transactional
    @Override
    public ProfileUserDto findProfileById(long id) {
        User user = userRepository.findOne(id);
        return new ProfileUserDto(user);
    }

    @Override
    public RegisterDto save(RegisterDto userDto) {
        if (userInformationRepository.findByEmail(userDto.getEmail()) != null) {
            throw new ExistsException("user with this email exists");
        }
        if (userInformationRepository.findByNickname(userDto.getNickname()) != null) {
            throw new ExistsException("user with this nickname exists");
        }

        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = new User();

        Set<Roles> roles = new HashSet<>();
        user.setInfo(new UserInformation());
        roles.add(Roles.USER);
        user.getInfo().setRoles(roles);
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setPatronymic(userDto.getPatronymic());
        user.getInfo().setEmail(userDto.getEmail());
        user.getInfo().setPassword(userDto.getPassword());
        user.getInfo().setNickname(userDto.getNickname());
        user.setSubjects(new HashMap<>());
        userRepository.save(user);
        userDto.setId(user.getId());

        if (userDto.getUserSubjects() != null){
            userDto.getUserSubjects().forEach(o -> subjectService.addSubjectToUser(userDto.getId(),o.getId(), o.getScore()));
        }
        return userDto;
    }

    @Transactional
    @Override
    public List<UserDto> findAll(Pageable pageable) {
        List<User> users = userRepository.findAll(pageable).getContent();
        return users.stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void remove(long id) {
        if (userRepository.exists(id)) {
            userRepository.delete(id);
        } else {
            throw new NotFoundException(String.format("user with id: %d not found", id));
        }
    }

    @Transactional
    public void changeStateOfRequest(long userId, boolean value) {
        User user = userRepository.findOne(userId);

        if (value) {
            if (user.getGroup() == null) {
                throw new ExistsException("user not select group");
            }
            user.getInfo().getRoles().add(Roles.VERIFIED);
        } else {
            user.setGroup(null);
            user.getInfo().getRoles().remove(Roles.VERIFIED);
        }
    }

    @Transactional
    @Override
    public void selectGroup(long userId, long groupId) {
        Group group = groupRepository.findOne(groupId);

        User user = userRepository.findOne(userId);
        if (!group.getSubjects().containsAll(user.getSubjects().keySet())) {
            throw new ConflictException("group subjects not equals your subjects");
        }
        user.setGroup(group);
        user.getInfo().getRoles().remove(Roles.VERIFIED);
    }

    @Transactional
    @Override
    public void setPhotoToUser(Long myId, String url) {
        userRepository.findOne(myId).setPhotoUrl(url);
    }

    @Transactional
    @Override
    public List<UserDto> getRequests() {
        return userRepository.getRequests().stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void update(ProfileUserDto userDto) {
        User user = userRepository.findOne(userDto.getId());

        user.setPatronymic(userDto.getPatronymic());
        user.setLastname(userDto.getLastname());
        user.setName(userDto.getName());

        Map<Subject, Integer> subjects = new HashMap<>();

        userDto.getMarks().forEach( dto -> {
            Subject subject = new Subject();
            subject.setId(dto.getId());
            subject.setSubject(dto.getSubject());
            subjects.put(subject, dto.getScore());
        });

        user.setSubjects(subjects);
    }

    @Override
    @Transactional
    public void removeAll() {
        userRepository.deleteAll();
    }
}
