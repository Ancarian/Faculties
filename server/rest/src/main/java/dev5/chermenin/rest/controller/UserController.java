package dev5.chermenin.rest.controller;

import dev5.chermenin.rest.security.service.AuthenticationServiceImpl;
import dev5.chermenin.service.api.UserInformationService;
import dev5.chermenin.service.api.UserService;
import dev5.chermenin.service.dto.impl.user.ProfileUserDto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by Ancarian on 22.10.2017.
 */


@RestController
@CrossOrigin(origins = "${cross.origin}")
@RequestMapping("/users")
@Api(value = "/users", description = "User controller")
@RequiredArgsConstructor
public class UserController {
    private final UserInformationService userInformationService;
    private final AuthenticationServiceImpl authenticationService;
    private final UserService userService;


    @PreAuthorize("hasAuthority('USER')")
    @ApiOperation(value = "add request")
    @RequestMapping(value = "/request/{groupId}", method = RequestMethod.PUT)
    public ResponseEntity addRequest(@PathVariable(value = "groupId") long groupId) {
        userService.selectGroup(authenticationService.getMyId(), groupId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "find user by Id")
    @RequestMapping(value = "/id/{userId}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> findById(@PathVariable(value = "userId") long userId) {
        UserDto userDto = userService.findById(userId);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "find user by name")
    @RequestMapping(value = "/name/{userName}", method = RequestMethod.GET)
    public ResponseEntity<UserDto> findByName(@PathVariable(value = "userName") String userName) {
        return new ResponseEntity<>(userService.findById(userInformationService.findByNickname(userName).getId()),
                HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "get all users")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> allUsers(Pageable pageable) {
        return new ResponseEntity<>(userService.findAll(pageable), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "remove user")
    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity removeUser(@PathVariable(value = "userId") long id) {
        userService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "update user")
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@Valid @RequestBody ProfileUserDto userDto) {
        userDto.setId(authenticationService.getMyId());
        userService.update(userDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
