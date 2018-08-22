package dev.chermenin.service.api;

import dev.chermenin.model.impl.User;
import dev.chermenin.dao.dto.user.UserDto;
import dev.chermenin.dao.dto.user.UserProfileDto;
import dev.chermenin.dao.dto.create.UserRegistrationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface UserService {
    UserProfileDto findById(long id);

    UserProfileDto findByNickname(String nickname);

    UserProfileDto findByEmail(String email);

    UserProfileDto save(UserRegistrationDto userRegistrationDto);

    User saveUser(UserRegistrationDto userRegistrationDto);

    Page<UserDto> findAll(Pageable pageable);

    Page<UserDto> findAll(Pageable pageable, Specification<User> specification);

    Page<UserDto> getRequests(Long id, Pageable pageable);

    void remove(long id);
}
