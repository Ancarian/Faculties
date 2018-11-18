package dev.chermenin.rest.api;

import dev.chermenin.dao.dto.create.GroupRegistrationDto;
import dev.chermenin.dao.dto.faculty.FacultyDto;
import dev.chermenin.dao.dto.group.GroupDto;
import dev.chermenin.service.api.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "${cross.origin}")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "Group controller")
@RequiredArgsConstructor
public class GroupController {
    private final GroupService groupService;

    @ApiOperation(value = "get group by id")
    @RequestMapping(value = "/group/{id}", method = RequestMethod.GET)
    public ResponseEntity<GroupDto> findbyId(@PathVariable("id") long id) {
        return new ResponseEntity<>(groupService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "get group by page")
    @RequestMapping(value = "/group", method = RequestMethod.GET)
    public ResponseEntity<Page<GroupDto>> findAll(Pageable pageable,
                                                  @RequestParam(value = "faculty_id", required = false) Long facultyId) {

        if (facultyId != null) {
            return ResponseEntity.ok(groupService.findAllByFacultyId(pageable, facultyId));
        }
        return ResponseEntity.ok(groupService.findAll(pageable));
    }

    @ApiOperation(value = "save group")
    @RequestMapping(value = "/group", method = RequestMethod.POST)
    public ResponseEntity<Page<FacultyDto>> saveGroup(@RequestBody GroupRegistrationDto groupRegistrationDto) {
        groupService.save(groupRegistrationDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "update faculty")
    @RequestMapping(value = "/group", method = RequestMethod.PUT)
    public ResponseEntity<Page<FacultyDto>> updateGroup(@RequestBody GroupRegistrationDto groupRegistrationDto) {
        groupService.update(groupRegistrationDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
