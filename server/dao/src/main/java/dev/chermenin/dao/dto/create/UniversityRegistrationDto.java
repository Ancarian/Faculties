package dev.chermenin.dao.dto.create;

import dev.chermenin.dao.dto.Dto;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

@Getter
@Setter
public class UniversityRegistrationDto implements Dto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String shortName;
    private String information;
    private String adress;
    private Long cityId;
}
