package dev5.chermenin.rest.security.service;

import dev5.chermenin.dao.repository.UserInformationRepository;
import dev5.chermenin.rest.security.SecurityHelper;
import dev5.chermenin.rest.security.model.JwtUserDetails;
import dev5.chermenin.service.api.UserService;
import dev5.chermenin.service.dto.impl.login.LoginRequestDto;
import dev5.chermenin.service.dto.impl.login.LoginResponseDto;
import dev5.chermenin.service.dto.impl.user.ProfileUserDto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

/**
 * Created by Ancarian on 18.02.2018.
 */
@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl {

    private final UserInformationRepository userInformationRepository;
    private final UserService userService;
    private final AuthenticationHelper authenticationHelper;
    private final AuthenticationManager authenticationManager;

    public LoginResponseDto login(final LoginRequestDto loginRequestDto) {
        try {
            String username = Optional.ofNullable(loginRequestDto.getUsername())
                    .orElseThrow(() -> new BadCredentialsException("Username should be passed."));
            String password = Optional.ofNullable(loginRequestDto.getPassword())
                    .orElseThrow(() -> new BadCredentialsException("Password should be passed."));
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,
                    password);

            // Try to authenticate with this token
            final Authentication authResult = this.authenticationManager.authenticate(authRequest);

            // Set generated JWT token to response header
            if (authResult.isAuthenticated()) {
                JwtUserDetails userDetails = (JwtUserDetails) authResult.getPrincipal();
                UserDto user = userService.findById(userDetails.getId());
                if (Objects.isNull(user)) {
                    throw new RuntimeException("User not exist in system.");
                }

                String token = this.authenticationHelper.generateToken(userDetails);
                return new LoginResponseDto(token);
            } else {
                throw new RuntimeException("Authentication failed.");
            }

        } catch (BadCredentialsException exception) {
            throw new RuntimeException("Username or password was incorrect. Please try again.", exception);
        }
    }

    @Transactional(readOnly = true)
    public ProfileUserDto getMe() {
        Authentication authentication = SecurityHelper.getAuthenticationWithCheck();
        return userService.findProfileById(userInformationRepository.findByNickname(authentication.getName()).getId());
    }

    @Transactional(readOnly = true)
    public Long getMyId() {
        Authentication authentication = SecurityHelper.getAuthenticationWithCheck();
        return userInformationRepository.findByNickname(authentication.getName()).getId();
    }
}