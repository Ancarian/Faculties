package dev5.chermenin.model.entity.impl;

import dev5.chermenin.model.entity.BaseObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "faculties", indexes = {@Index(name = "id_index_faculties", columnList = "id", unique = true)})
public class Faculty extends BaseObject {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "information", unique = true, nullable = false)
    private String information;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private List<Group> groups;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "universities_id", nullable = false)
    private University university;

}
