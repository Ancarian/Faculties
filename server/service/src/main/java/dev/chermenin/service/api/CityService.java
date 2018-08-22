package dev.chermenin.service.api;

import dev.chermenin.dao.dto.adress.CityDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService {
    List<CityDto> findAllByStateId(Long stateId, String name, Pageable pageable);
}
