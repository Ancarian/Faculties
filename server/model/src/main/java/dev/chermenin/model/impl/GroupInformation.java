package dev.chermenin.model.impl;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "group_information")
@Getter
@Setter
public class GroupInformation extends BaseObject{
    @Column(unique = true, nullable = false, length = 30)
    private String name;

    @Column(unique = true, nullable = false, length = 30)
    private String code;

    @Column(unique = true, nullable = false, length = 8)
    private String shortName;

    @Column(nullable = false, length = 512)
    private String information;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "faculties_id", nullable = false)
    private Faculty faculty;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "groups_subjects", joinColumns = {@JoinColumn(name = "groups_id")},
            inverseJoinColumns = {@JoinColumn(name = "subjects_id")})
    private Set<Subject> subjects;

    @Column(name = "qualify", nullable = false)
    private String qualify;
}
