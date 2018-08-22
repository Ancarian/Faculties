package dev.chermenin.rest.api;


import dev.chermenin.dao.dto.create.UserRegistrationDto;
import dev.chermenin.dao.dto.login.LoginRequestDto;
import dev.chermenin.dao.dto.login.LoginResponseDto;
import dev.chermenin.dao.dto.user.UserDto;
import dev.chermenin.dao.dto.user.UserProfileDto;
import dev.chermenin.model.impl.EmailVerificationToken;
import dev.chermenin.model.impl.User;
import dev.chermenin.rest.security.service.AuthService;
import dev.chermenin.service.api.EmailService;
import dev.chermenin.service.api.TokenVerificationService;
import dev.chermenin.service.api.UserService;
import dev.chermenin.service.api.UserUpdateService;
import dev.chermenin.service.exception.BadRequestException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Ancarian on 18.02.2018.
 */

@RestController
@CrossOrigin(origins = "${cross.origin}")
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "auth", description = "Auth controller")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authenticationService;
    private final UserUpdateService userUpdateService;
    private final UserService userService;
    private final EmailService emailService;
    private final TokenVerificationService tokenVerificationService;

    @ApiOperation(value = "login")
    @PostMapping(value = "/login")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<LoginResponseDto> login(@RequestBody @NotNull final LoginRequestDto loginRequestDto) throws Exception{
        return new ResponseEntity<>(authenticationService.login(loginRequestDto), HttpStatus.OK);
    }

    @ApiOperation(value = "sign up")
    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public ResponseEntity register(@Valid @NotNull @RequestBody UserRegistrationDto userDto, final HttpServletRequest request) throws Exception{
        User savedUser = userService.saveUser(userDto);
        EmailVerificationToken token  = tokenVerificationService.createVerificationToken(savedUser);
        emailService.confirmRegistration(token, request.getLocale());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "registration confirm")
    @GetMapping(value = "/registrationConfirm")
    public ResponseEntity<UserProfileDto> regitrationConfirm(@RequestParam("token") String token) {
        EmailVerificationToken verificationToken = tokenVerificationService.findByToken(token);
        User user = verificationToken.getUser();
        if (tokenVerificationService.isExpired(verificationToken)) {
            throw new BadRequestException("token has been expired");
        }
        userUpdateService.enableUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "profile")
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/me")
    public ResponseEntity<UserProfileDto> me() {
        return new ResponseEntity<>(authenticationService.getUserByAuthentication(), HttpStatus.OK);
    }


}