package dev.chermenin.rest.api;


import dev.chermenin.dao.dto.create.FacultyRegistrationDto;
import dev.chermenin.dao.dto.faculty.FacultyDto;
import dev.chermenin.service.api.FacultyService;
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
@Api(description = "Faculty controller")
@RequiredArgsConstructor
public class FacultyController {
    private final FacultyService facultyService;

    @ApiOperation(value = "get faculty by id")
    @RequestMapping(value = "/faculty/{id}", method = RequestMethod.GET)
    public ResponseEntity<FacultyDto> findbyId(@PathVariable("id") long id) {
        return new ResponseEntity<>(facultyService.findById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "get all faculties by page")
    @RequestMapping(value = "/faculty", method = RequestMethod.GET)
    public ResponseEntity<Page<FacultyDto>> findAllFaculties(Pageable pageable,
                                                             @RequestParam(value = "univer_id", required = false) Long univerId) {
        if (univerId != null) {
            return new ResponseEntity<>(facultyService.findAllByUniversityId(pageable, univerId), HttpStatus.OK);
        }
        return new ResponseEntity<>(facultyService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "save faculty")
    @RequestMapping(value = "/faculty", method = RequestMethod.POST)
    public ResponseEntity<Page<FacultyDto>> saveFaculty(@RequestBody FacultyRegistrationDto facultyDto) {
        facultyService.save(facultyDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "update faculty")
    @RequestMapping(value = "/faculty", method = RequestMethod.PUT)
    public ResponseEntity<Page<FacultyDto>> updateFaculty(@RequestBody FacultyRegistrationDto facultyDto) {
        facultyService.update(facultyDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
