package dev5.chermenin.service.dto.impl;

import dev5.chermenin.model.entity.BaseObject;
import dev5.chermenin.model.entity.impl.Faculty;
import dev5.chermenin.model.entity.impl.Group;
import dev5.chermenin.model.entity.impl.User;
import dev5.chermenin.service.dto.Dto;
import dev5.chermenin.service.dto.impl.subject.SubjectDto;
import dev5.chermenin.service.dto.impl.user.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class GroupDto extends Dto {

    @Min(value = 0)
    private int countOfUsers;

    @Min(value = 0)
    private int countOfAllUsers;

    @Pattern(regexp = "[a-zA-Z0-9_]{100,200}+")
    private String information;

    @NotNull
    private Date issueDate;

    @NotNull
    private Date validTill;

    @NotNull
    private List<SubjectDto> subjects;

    @Min(value = 1)
    private int limit;

    @Min(value = 0)
    private BigInteger enrollMark;

    @Pattern(regexp = "[a-zA-Z0-9_]{6,20}+")
    private String qualify;

    @NotNull
    private FacultyComponentDto facultyComponentDto;

    private List<BigInteger> users;

    public GroupDto(Group group) {
        this.setId(group.getId());
        countOfUsers = group.getCountOfUsers();
        countOfAllUsers = group.getCountOfAllUsers();
        information = group.getInformation();
        issueDate = group.getIssueDate();
        validTill = group.getValidTill();
        limit = group.getLimit();
//        enrollMark = group.getEnrollMark();
        qualify = group.getQualify();

        if (group.getFaculty().getId() != null) {
            facultyComponentDto = new FacultyComponentDto(group.getFaculty());
        }

        if (group.getSubjects() != null) {
            subjects = group.getSubjects().stream()
                    .map(SubjectDto::new)
                    .collect(Collectors.toList());
        }

//        if (group.getUsers() != null) {
//            users = group.getUsers().stream()
//                    .map(UserDto::new)
//                    .collect(Collectors.toList());
//
//            enrollMark = group.getUsers()
//                    .stream()
//                    .map(user -> user.getSubjects()
//                            .values()
//                            .stream()
//                            .mapToInt(Integer::intValue)
//                            .sum())
//                    .max(Integer::compare)
//                    .get();
//        }
    }

    @Setter
    @Getter
    public class FacultyComponentDto extends Dto{
        private String name;
        private String information;

        public FacultyComponentDto(Faculty faculty){
            setId(faculty.getId());
            name = faculty.getName();
            information = faculty.getInformation();
        }

        public FacultyComponentDto(){}
    }
}

