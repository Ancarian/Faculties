package dev5.chermenin.rest.controller;

import dev5.chermenin.service.api.FacultyService;
import dev5.chermenin.service.dto.impl.FacultyDto;
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

@RestController
@CrossOrigin(origins = "${cross.origin}")
@Api(description = "Faculty controller")
@RequestMapping("/faculties")
@RequiredArgsConstructor
public class FacultyController {
    private final FacultyService facultyService;

    @ApiOperation(value = "get all faculties")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<FacultyDto>> getAllFaculties(Pageable pageable) {
        List<FacultyDto> facultyDtos = facultyService.findAll(pageable);
        return new ResponseEntity<>(facultyDtos, HttpStatus.OK);
    }

    @ApiOperation(value = "get faculty by id")
    @RequestMapping(value = "/{facultyId}", method = RequestMethod.GET)
    public ResponseEntity<FacultyDto> getFaculty(@PathVariable(value = "facultyId") long id) {
        FacultyDto facultyDto = facultyService.findById(id);
        return new ResponseEntity<>(facultyDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    @ApiOperation(value = "save new faculty")
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<FacultyDto> addFaculty(@Valid @NotNull @RequestBody FacultyDto facultyDto) {
        return new ResponseEntity<>(facultyService.save(facultyDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    @ApiOperation(value = "remove faculty")
    @RequestMapping(value = "/{facultyId}", method = RequestMethod.DELETE)
    public ResponseEntity removeFaculty(@PathVariable(value = "facultyId") long id) {
        facultyService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MODERATOR')")
    @ApiOperation(value = "update faculty")
    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public ResponseEntity updateFaculty(@Valid @NotNull @RequestBody FacultyDto facultyDto) {
        facultyService.update(facultyDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
