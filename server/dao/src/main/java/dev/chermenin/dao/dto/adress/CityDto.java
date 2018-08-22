package dev.chermenin.dao.dto.adress;

import dev.chermenin.dao.dto.Dto;
import dev.chermenin.model.impl.adress.City;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityDto implements Dto {
    private String name;
    private Long id;

    public CityDto(City city) {
        this.id = city.getId();
        this.name = city.getName();
    }
}
