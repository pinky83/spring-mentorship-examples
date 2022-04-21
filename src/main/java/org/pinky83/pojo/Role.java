package org.pinky83.pojo;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Transient;

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    @Transient
    public String getAuthority() {
        return name();
    }
}
