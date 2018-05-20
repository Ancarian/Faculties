package dev5.chermenin.service.api;

import dev5.chermenin.service.dto.impl.user.ProfileUserDto;
import dev5.chermenin.service.dto.impl.user.RegisterDto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserDto findById(long id);

    ProfileUserDto findProfileById(long id);

    RegisterDto save(RegisterDto profileUserDto);

    List<UserDto> findAll(Pageable pageable);

    void remove(long id);

    List<UserDto> getRequests();

    void update(ProfileUserDto profileUserDto);

    void removeAll();

    void selectGroup(long userId, long groupId);

    void changeStateOfRequest(long userId, boolean value);

    void setPhotoToUser(Long myId, String load);
}

