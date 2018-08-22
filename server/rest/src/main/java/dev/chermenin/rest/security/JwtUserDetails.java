package dev.chermenin.rest.security;

import dev.chermenin.dao.dto.Credentials;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Ancarian on 18.02.2018.
 */
public class JwtUserDetails implements UserDetails {
    private Long id;
    private String username;
    private String password;
    private Set<GrantedAuthority> authorities;
    private boolean enabled;

    public JwtUserDetails(Credentials userCredentialsDto) {
        this.id = userCredentialsDto.getId();
        this.username = userCredentialsDto.getEmail();
        this.password = userCredentialsDto.getPassword();
        this.enabled = userCredentialsDto.isEnabled();
        this.authorities = new HashSet<>();
        this.authorities.add(new SimpleGrantedAuthority(userCredentialsDto.getRole().toString()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}