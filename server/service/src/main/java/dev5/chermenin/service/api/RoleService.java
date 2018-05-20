package dev5.chermenin.service.api;

import dev5.chermenin.model.entity.impl.enums.Roles;

import java.util.Set;

public interface RoleService {
    void addRoleToUser(long id, Roles role);

    void removeUserRole(long id, Roles role);

    Set<Roles> getRolesByUserId(Long id);
}

