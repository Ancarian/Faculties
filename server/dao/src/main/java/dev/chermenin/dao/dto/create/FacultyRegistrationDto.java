package dev.chermenin.dao.dto.create;

import dev.chermenin.dao.dto.Dto;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class FacultyRegistrationDto implements Dto {
    private Long id;
    private String email;
    private String phone;
    private String name;
    private String shortName;
    private String information;
    private Long universityId;
}