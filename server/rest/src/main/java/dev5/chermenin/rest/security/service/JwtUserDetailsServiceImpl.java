package dev5.chermenin.rest.security.service;

import dev5.chermenin.dao.repository.UserInformationRepository;
import dev5.chermenin.model.entity.impl.UserInformation;
import dev5.chermenin.rest.security.model.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Ancarian on 18.02.2018.
 */
@Service
@RequiredArgsConstructor
public class JwtUserDetailsServiceImpl implements UserDetailsService {
    private final UserInformationRepository userInformationRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInformation byUsername = this.userInformationRepository.findByNickname(username);

        return Optional.ofNullable(byUsername)
                .map(JwtUserDetails::new)
                .orElseThrow(() -> new RuntimeException("User not found."));
    }
}
