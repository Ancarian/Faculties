package dev.chermenin.service.api.impl;


import dev.chermenin.dao.CityRepository;
import dev.chermenin.dao.CountryRepository;
import dev.chermenin.dao.dto.adress.CityDto;
import dev.chermenin.model.impl.adress.City;
import dev.chermenin.service.api.CityService;
import dev.chermenin.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Override
	public Page<CityDto> findAllByCountryId(Long countryId, String name, Pageable pageable) {
		if (!countryRepository.existsById(countryId)) {
            throw new NotFoundException("error.city.notExistsId");
        }
		Page<City> cities;
		if (name != null && !name.isEmpty()) {
			cities = cityRepository
					.findAllByCountryIdAndNameLike(countryId, name, pageable);
		} else {
			cities = cityRepository
					.findAllByCountryId(countryId, pageable);
		}
		return cities.map(CityDto::new);
    }
}
