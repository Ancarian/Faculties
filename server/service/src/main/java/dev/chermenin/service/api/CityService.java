package dev.chermenin.service.api;

import dev.chermenin.dao.dto.adress.CityDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService {
	Page<CityDto> findAllByCountryId(Long stateId, String name, Pageable pageable);
}
