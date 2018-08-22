package dev.chermenin.rest.security.service;

import dev.chermenin.dao.UserRepository;
import dev.chermenin.dao.dto.Credentials;
import dev.chermenin.rest.security.JwtUserDetails;
import dev.chermenin.service.api.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Qualifier("UserDetailsServiceImpl")
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Credentials user = userRepository.getCredentialsByEmail(email).
                orElseThrow(() -> new UsernameNotFoundException(String.format("The username %s doesn't exist", email)));
        return new JwtUserDetails(user);
    }
}

