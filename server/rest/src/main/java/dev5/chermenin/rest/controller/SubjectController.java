package dev5.chermenin.rest.controller;

import dev5.chermenin.rest.security.service.AuthenticationServiceImpl;
import dev5.chermenin.service.api.SubjectService;
import dev5.chermenin.service.dto.impl.subject.SubjectDto;
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
import java.util.Set;

/**
 * Created by Ancarian on 24.12.2017.
 */

@CrossOrigin(origins = "${cross.origin}")
@RestController
@RequestMapping("/subjects")
@Api(description = "Subject controller")
@RequiredArgsConstructor
public class SubjectController {
    private final SubjectService subjectService;
    private final AuthenticationServiceImpl authenticationService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    @ApiOperation(value = "save new subject")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<SubjectDto> saveSubject(@Valid @RequestBody SubjectDto subjectDto) {
        return new ResponseEntity<>(subjectService.save(subjectDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "get all subjects")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SubjectDto>> getAll(Pageable pageable) {
        return new ResponseEntity<>(subjectService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "get inventory subjects")
    @RequestMapping(value = "/indendatory_ids",method = RequestMethod.PUT)
    public ResponseEntity<List<SubjectDto>> getInventorySubjects(@RequestBody List<Long> ids) {
        return new ResponseEntity<>(subjectService.findSubjectsByInventoryIds(ids), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    @ApiOperation(value = "remove subject")
    @RequestMapping(value = "/{subjectId}", method = RequestMethod.DELETE)
    public ResponseEntity removeSubject(@PathVariable(value = "subjectId") long id) {
        subjectService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation(value = "find subject by id")
    @RequestMapping(value = "/id/{subjectId}", method = RequestMethod.GET)
    public ResponseEntity<SubjectDto> findSubjectById(@PathVariable(value = "subjectId") long id) {
        return new ResponseEntity<>(subjectService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "find subject by name")
    @RequestMapping(value = "/name/{subjectName}", method = RequestMethod.GET)
    public ResponseEntity<SubjectDto> findSubjectByName(@PathVariable(value = "subjectName") String subjectName) {
        return new ResponseEntity<>(subjectService.findByName(subjectName), HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @ApiOperation(value = "addSubjectToUser")
    @RequestMapping(value = "/change_state/{subjectId}/{mark}", method = RequestMethod.PUT)
    public ResponseEntity addSubjectToUser(@PathVariable(value = "subjectId") long subjectId,
                                           @PathVariable(value = "mark") int mark) {

        subjectService.addSubjectToUser(authenticationService.getMyId(), subjectId, mark);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('USER')")
    @ApiOperation(value = "removeUserSubject")
    @RequestMapping(value = "/change_state/{subjectId}", method = RequestMethod.DELETE)
    public ResponseEntity removeUserSubject(@PathVariable(value = "subjectId") long subjectId) {
        subjectService.removeUserSubject(authenticationService.getMyId(), subjectId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
