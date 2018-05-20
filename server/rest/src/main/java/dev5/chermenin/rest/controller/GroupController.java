package dev5.chermenin.rest.controller;

import dev5.chermenin.service.api.GroupService;
import dev5.chermenin.service.dto.impl.GroupDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Ancarian
 * 06.12.2017
 */

@CrossOrigin(origins = "${cross.origin}")
@RestController
@RequestMapping(value = "/groups")
@Api(description = "Group controller")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @ApiOperation(value = "get all groups")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<GroupDto>> allGroups(Pageable pageable) {
        return new ResponseEntity<>(groupService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "get group by id")
    @RequestMapping(value = "/{groupId}", method = RequestMethod.GET)
    public ResponseEntity<GroupDto> getGroup(@PathVariable(value = "groupId") long id) {
        GroupDto groupDto = groupService.findById(id);
        return new ResponseEntity<>(groupDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    @ApiOperation(value = "save new group")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<GroupDto> addGroup(@Valid @NotNull @RequestBody GroupDto groupDto) {
        return new ResponseEntity<>(groupService.save(groupDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    @ApiOperation(value = "remove group")
    @RequestMapping(value = "/{groupId}", method = RequestMethod.DELETE)
    public ResponseEntity removeGroup(@PathVariable(value = "groupId") long id) {
        groupService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    @ApiOperation(value = "update group")
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity updateGroup(@Valid @NotNull @RequestBody GroupDto groupDto) {
        groupService.update(groupDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
