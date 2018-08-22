package dev.chermenin.service.api.impl;


import dev.chermenin.dao.CountryRepository;
import dev.chermenin.dao.dto.adress.CountryDto;
import dev.chermenin.model.impl.adress.Country;
import dev.chermenin.service.api.CountryService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Override
    public List<CountryDto> findAll() {
        List<Country> countries = countryRepository.findAll();
        return countries.stream().map(CountryDto::new).collect(Collectors.toList());
    }

}
