package dev.chermenin.dao.dto.group;

import dev.chermenin.model.impl.Faculty;
import dev.chermenin.model.impl.GroupInformation;
import dev.chermenin.model.impl.Subject;
import lombok.Value;

import javax.persistence.*;
import java.util.Set;

@Value
public class GroupInformationDto {
    private String name;
    private String code;
    private String shortName;
    private String information;
    private Set<Subject> subjects;
    private String qualify;

    public GroupInformationDto(GroupInformation groupInformation) {
        this.name = groupInformation.getName();
        this.code = groupInformation.getCode();
        this.shortName = groupInformation.getShortName();
        this.information = groupInformation.getInformation();
        this.subjects = groupInformation.getSubjects();
        this.qualify = groupInformation.getQualify();
    }
}
