package dev.chermenin.dao.dto.university;

import dev.chermenin.dao.dto.Dto;
import dev.chermenin.model.impl.University;
import dev.chermenin.model.impl.adress.City;
import lombok.Value;

@Value
public class UniversityDto implements Dto {
    private String name;
    private String email;
    private String phone;
    private String shortName;
    private String information;
    private String adress;
    private String city;
    private Long id;

    public UniversityDto(University university) {
        this.id = university.getId();
        this.name = university.getName();
        this.email = university.getEmail();
        this.phone = university.getPhone();
        this.shortName = university.getShortName();
        this.information = university.getInformation();
        this.adress = university.getAdress();
        this.city = university.getCity().getName();
    }

    public UniversityDto(String name, String email, String phone, String shortName, String information, String adress, City city, Long id) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.shortName = shortName;
        this.information = information;
        this.adress = adress;
        this.city = city.getName();
        this.id = id;
    }
}
