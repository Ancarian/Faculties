package dev.chermenin.rest.api;

import dev.chermenin.dao.dto.adress.CountryDto;
import dev.chermenin.service.api.CountryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "${cross.origin}")
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "Country controller")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;

    @ApiOperation(value = "get all countries")
    @RequestMapping(value = "/countries", method = RequestMethod.GET)
    public ResponseEntity<List<CountryDto>> getAllCountries() {
        return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }
}
