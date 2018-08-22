package dev.chermenin.dao.dto;

import dev.chermenin.model.impl.enums.Roles;
import lombok.*;

public interface Credentials {
    Long getId();
    String getEmail();
    String getPassword();
    Roles getRole();
    boolean isEnabled();
}

