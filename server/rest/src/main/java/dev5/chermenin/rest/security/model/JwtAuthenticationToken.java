package dev5.chermenin.rest.security.model;

import com.google.common.collect.ImmutableSet;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

/**
 * Created by Ancarian on 18.02.2018.
 */

public class JwtAuthenticationToken implements Authentication {

    private final JwtUserDetails userDetails;

    private final Serializable credentials;
    private final Collection<? extends GrantedAuthority> grantedAuthorities;
    private boolean isAuthenticated;

    public JwtAuthenticationToken(final String token) {
        this.credentials = token;
        this.userDetails = null;
        this.grantedAuthorities = null;
    }

    public JwtAuthenticationToken(final JwtUserDetails userDetails) {
        this.credentials = null;
        this.userDetails = userDetails;
        this.grantedAuthorities = ImmutableSet.copyOf(userDetails.getAuthorities());
        this.isAuthenticated = true;
    }

    @Override
    public String getName() {
        return Objects.isNull(this.userDetails) ? null : this.userDetails.getUsername();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public Serializable getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getDetails() {
        return this.userDetails;
    }

    @Override
    public Object getPrincipal() {
        return this.userDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return this.isAuthenticated;
    }

    @Override
    public void setAuthenticated(final boolean isAuthenticated) {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Once created you cannot set this token to authenticated.");
        }
        this.isAuthenticated = false;
    }
}