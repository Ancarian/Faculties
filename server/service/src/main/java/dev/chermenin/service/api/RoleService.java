package dev.chermenin.service.api;


import dev.chermenin.model.impl.enums.Roles;

import java.util.Set;

public interface RoleService {
    void changeUserRole(long id, Roles role);
}

