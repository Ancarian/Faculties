package dev5.chermenin.model.entity.impl;

import dev5.chermenin.model.entity.BaseObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "universities", indexes = {@Index(name = "id_index_universities", columnList = "id", unique = true)})
public class University extends BaseObject {

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private Set<Faculty> faculties;


}
