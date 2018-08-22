package dev.chermenin.dao.dto.adress;


import dev.chermenin.dao.dto.Dto;
import dev.chermenin.model.impl.adress.Country;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDto implements Dto {
    private Integer phoneCode;
    private String shortName;
    private String name;
    private Long id;

    public CountryDto(Country country) {
        this.id = country.getId();
        this.phoneCode = country.getPhoneCode();
        this.shortName = country.getShortName();
        this.name = country.getName();
    }
}
