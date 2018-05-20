package dev5.chermenin.rest.controller;


import dev5.chermenin.service.api.UniversityService;
import dev5.chermenin.service.dto.impl.UniversityDto;
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

@CrossOrigin(origins = "${cross.origin}")
@RestController

@RequestMapping(value = "/universities")
@Api(description = "University controller")
@RequiredArgsConstructor
public class UniversityController {

    private final UniversityService universityService;

    @ApiOperation(value = "get all universities")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<UniversityDto>> allUniversities(Pageable pageable) {
        return new ResponseEntity<>(universityService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "get university by id")
    @RequestMapping(value = "/{universityId}", method = RequestMethod.GET)
    public ResponseEntity<UniversityDto> getUniversity(@PathVariable(value = "universityId") long id) {
        UniversityDto universityDto = universityService.findById(id);
        return new ResponseEntity<>(universityDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @ApiOperation(value = "save new university")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<UniversityDto> addUniversity(@Valid @NotNull @RequestBody UniversityDto universityDto) {
        return new ResponseEntity<>(universityService.save(universityDto), HttpStatus.CREATED);
    }
}
