package dev.chermenin.service.api;


import dev.chermenin.dao.dto.adress.CountryDto;

import java.util.List;

public interface CountryService {
    List<CountryDto> findAll();

}
