package dev5.chermenin.rest.controller;

import dev5.chermenin.model.entity.impl.enums.Roles;
import dev5.chermenin.rest.security.service.AuthenticationServiceImpl;
import dev5.chermenin.service.api.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Created by Ancarian on 24.12.2017.
 */

@CrossOrigin(origins = "${cross.origin}")
@RestController
@RequestMapping("/roles")
@Api(description = "Role controller")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final AuthenticationServiceImpl authenticationService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "add role to user")
    @RequestMapping(value = "/{user_id}/{user_role}", method = RequestMethod.PUT)
    public ResponseEntity addRoleToUser(@PathVariable(value = "user_id") long user_id,
                                        @PathVariable(value = "user_role") String user_role) {
        roleService.addRoleToUser(user_id, Roles.valueOf(user_role));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "remove user role")
    @RequestMapping(value = "/{user_id}/{user_role}", method = RequestMethod.DELETE)
    public ResponseEntity removeUserRole(@PathVariable(value = "user_id") long user_id,
                                         @PathVariable(value = "user_role") String user_role) {
        roleService.removeUserRole(user_id, Roles.valueOf(user_role));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @ApiOperation(value = "get roles by user")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Set<Roles>> getRolesByUser() {
        return new ResponseEntity<>(roleService.getRolesByUserId(authenticationService.getMyId()), HttpStatus.OK);
    }
}
