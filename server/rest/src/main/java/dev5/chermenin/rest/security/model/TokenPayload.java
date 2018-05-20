package dev5.chermenin.rest.security.model;

import java.util.Set;

/**
 * Created by Ancarian on 18.02.2018.
 */
public class TokenPayload {
    private Long userId;
    private long exp;
    private Set<String> roles;

    public TokenPayload() {
    }

    public TokenPayload(final Long userId, final long exp) {
        this.userId = userId;
        this.exp = exp;
    }

    public TokenPayload(Long userId, long exp, Set<String> roles) {
        this.userId = userId;
        this.exp = exp;

        this.roles = roles;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public long getExp() {
        return exp;
    }

    public void setExp(long exp) {
        this.exp = exp;
    }
}