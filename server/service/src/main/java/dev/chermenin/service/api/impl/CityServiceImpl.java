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
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Override
    public List<CityDto> findAllByStateId(Long stateId, String name, Pageable pageable) {
        if (!countryRepository.existsById(stateId)){
            throw new NotFoundException("error.city.notExistsId");
        }
        Page<City> cities = cityRepository
                .findAllByCountryIdAndNameLike(stateId, name, pageable);
        return cities.stream().map(CityDto::new).collect(Collectors.toList());
    }
}
