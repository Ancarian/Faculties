package dev.chermenin.rest.api;

import dev.chermenin.dao.dto.create.UniversityRegistrationDto;
import dev.chermenin.dao.dto.university.UniversityDto;
import dev.chermenin.service.api.UniversityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@CrossOrigin(origins = "${cross.origin}")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "University controller")
@RequiredArgsConstructor
public class UniversityController {
    private final UniversityService universityService;

    @ApiOperation(value = "get all universities")
    @RequestMapping(value = "/universities", method = RequestMethod.GET)
    public ResponseEntity<Page<UniversityDto>> allUniversities(Pageable pageable) {
        return new ResponseEntity<>(universityService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "get university by id")
    @RequestMapping(value = "/university/{universityId}", method = RequestMethod.GET)
    public ResponseEntity<UniversityDto> getUniversity(@PathVariable(value = "universityId") long id) {
        UniversityDto universityDto = universityService.findById(id);
        return new ResponseEntity<>(universityDto, HttpStatus.OK);
    }

    @ApiOperation(value = "save new university", response = UniversityRegistrationDto.class)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/university", method = RequestMethod.POST)
    public ResponseEntity<UniversityDto> addUniversity(@Valid @NotNull @RequestBody UniversityRegistrationDto universityDto) {
        return new ResponseEntity<>(universityService.save(universityDto), HttpStatus.CREATED);
    }

    @ApiOperation(value = "update university", response = UniversityRegistrationDto.class)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @RequestMapping(value = "/university", method = RequestMethod.PUT)
    public ResponseEntity updateUniversity(@Valid @NotNull @RequestBody UniversityRegistrationDto universityDto) {
        universityService.update(universityDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
