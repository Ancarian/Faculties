package dev.chermenin.service.api.impl;

import dev.chermenin.dao.UserRepository;
import dev.chermenin.dao.dto.user.UserProfileDto;
import dev.chermenin.model.impl.User;
import dev.chermenin.model.impl.enums.Roles;
import dev.chermenin.service.api.RoleService;
import dev.chermenin.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public void changeUserRole(long id, Roles role) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("error.user.NotExistsId"));
        user.setRole(role);
    }
}
