package dev.chermenin.rest.api;

import dev.chermenin.dao.dto.adress.CityDto;
import dev.chermenin.dao.dto.create.UniversityRegistrationDto;
import dev.chermenin.dao.dto.university.UniversityDto;
import dev.chermenin.service.api.CityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
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
@Api(description = "city controller")
@RequiredArgsConstructor
public class CityController {
	private final CityService cityService;

	@ApiOperation(value = "get all cities")
	@RequestMapping(value = "/cities", method = RequestMethod.GET)
	public ResponseEntity<Page<CityDto>> getCities(@RequestParam(value = "countryId") Long countryId,
												   @RequestParam(value = "cityName", required = false) String cityName, Pageable pageable) {
		return new ResponseEntity<>(cityService.findAllByCountryId(countryId, cityName, pageable), HttpStatus.OK);
	}
}
