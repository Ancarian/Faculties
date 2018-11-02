package dev.chermenin.rest.api;


import dev.chermenin.dao.dto.create.UserRegistrationDto;
import dev.chermenin.dao.dto.credentials.ResetPasswordDto;
import dev.chermenin.dao.dto.login.LoginRequestDto;
import dev.chermenin.dao.dto.login.LoginResponseDto;
import dev.chermenin.dao.dto.user.UserProfileDto;
import dev.chermenin.model.impl.EmailVerificationToken;
import dev.chermenin.model.impl.PasswordResetToken;
import dev.chermenin.model.impl.User;
import dev.chermenin.rest.security.service.AuthService;
import dev.chermenin.service.api.*;
import dev.chermenin.service.exception.BadRequestException;
import dev.chermenin.service.exception.ConflictException;
import dev.chermenin.service.exception.NotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    private final UserService userService;
    private final EmailService emailService;
    private final TokenVerificationService tokenVerificationService;
    private final PasswordService passwordService;

    @ApiOperation(value = "login")
    @PostMapping(value = "/login")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<LoginResponseDto> login(@RequestBody @NotNull final LoginRequestDto loginRequestDto) throws Exception {
        return new ResponseEntity<>(authenticationService.login(loginRequestDto), HttpStatus.OK);
    }

    @ApiOperation(value = "sign up")
    @RequestMapping(value = "/sign_up", method = RequestMethod.POST)
    public ResponseEntity register(@Valid @NotNull @RequestBody UserRegistrationDto userDto, final HttpServletRequest request) throws Exception {
        User savedUser = userService.saveUser(userDto);
        EmailVerificationToken token = tokenVerificationService.createVerificationToken(savedUser);
        emailService.confirmRegistration(token, request.getLocale());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PreAuthorize("!isAuthenticated()")
    @ApiOperation(value = "registration confirm")
    @GetMapping(value = "/registration_confirm")
    public ResponseEntity<UserProfileDto> regitrationConfirm(@RequestParam("token") String token) {
        tokenVerificationService.enableUser(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("!isAuthenticated()")
    @RequestMapping(value = "/resend_registration", method = RequestMethod.GET)
    public ResponseEntity<UserProfileDto> resendRegistrationToken(HttpServletRequest request
            , @RequestParam("token") String existingToken) throws Exception {
        EmailVerificationToken newToken = tokenVerificationService.generateNewVerificationToken(existingToken);
        emailService.confirmRegistration(newToken, request.getLocale());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "profile")
    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/me")
    public ResponseEntity<UserProfileDto> me() {
        return new ResponseEntity<>(authenticationService.getUserByAuthentication(), HttpStatus.OK);
    }


    @PreAuthorize("!isAuthenticated()")
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public RestExceptionHandler.GenericResponse resetPassword(HttpServletRequest request
            , @RequestParam("email") String userEmail) throws Exception {
        PasswordResetToken token = passwordService.resetPassword(userEmail);
        emailService.resetPassword(token, request.getLocale());
        return new RestExceptionHandler.GenericResponse("password has been reseted");
    }

    @PreAuthorize("!isAuthenticated()")
    @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
    public ResponseEntity changePassword(@RequestBody @NotNull final ResetPasswordDto resetPasswordDto
            , @RequestParam("id") long id, @RequestParam("token") String token) {
        passwordService.resetPassword(id, token, resetPasswordDto.getPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}