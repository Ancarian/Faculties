package dev5.chermenin.rest.controller;

import dev5.chermenin.service.api.UserService;
import dev5.chermenin.service.dto.impl.user.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ancarian on 06.12.2017.
 */



@CrossOrigin(origins = "${cross.origin}")
@RestController
@RequestMapping(value = "/requests")
@Api(value = "users", description = "Admin request controller")
@RequiredArgsConstructor
public class RequestController {
    private final UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "get requests")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<UserDto>> requests() {
        return new ResponseEntity<>(userService.getRequests(), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "confirm request")
    @RequestMapping(value = "/{userId}/confirm", method = RequestMethod.PUT)
    public ResponseEntity confirmRequest(@PathVariable(value = "userId") long userId) {
        userService.changeStateOfRequest(userId, true);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "cancel request")
    @RequestMapping(value = "/{userId}/cancel", method = RequestMethod.PUT)
    public ResponseEntity cancelRequest(@PathVariable(value = "userId") long userId) {
        userService.changeStateOfRequest(userId, false);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
