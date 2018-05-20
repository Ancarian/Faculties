package dev5.chermenin.rest.controller;

import dev5.chermenin.rest.security.service.AuthenticationServiceImpl;
import dev5.chermenin.service.api.UserInformationService;
import dev5.chermenin.service.dto.impl.credentials.CredentialEmailDto;
import dev5.chermenin.service.dto.impl.credentials.CredentialPasswordDto;
import dev5.chermenin.service.dto.impl.subject.SubjectDto;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Ancarian on 26.02.2018.
 */

@RestController
@RequestMapping(value = "/settings")
@CrossOrigin(origins = "${cross.origin}")
@RequiredArgsConstructor
public class UserInformationController {

    private final UserInformationService userInformationService;
    private final AuthenticationServiceImpl authenticationService;

    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "change password")
    @RequestMapping(value = "/change_password", method = RequestMethod.PUT)
    public ResponseEntity changePassword(@Valid @RequestBody CredentialPasswordDto credentialPasswordDto) {
        userInformationService.changePassword(credentialPasswordDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "change email")
    @RequestMapping(value = "/change_email", method = RequestMethod.PUT)
    public ResponseEntity changeEmail(@Valid @RequestBody CredentialEmailDto credentialEmailDto) {
        userInformationService.changeEmail(credentialEmailDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
