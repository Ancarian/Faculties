package dev5.chermenin.model.entity.impl;

import dev5.chermenin.model.entity.BaseObject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "users", indexes = {@Index(name = "id_index_faculties", columnList = "id", unique = true)})
public class User extends BaseObject {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "patronymic", nullable = false)
    private String patronymic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groups_id")
    private Group group;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private UserInformation info;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "users_subjects", joinColumns = {@JoinColumn(name = "users_id")})
    @MapKeyJoinColumn(name = "subjects_id")
    @Column(name = "mark")
    private Map<Subject, Integer> subjects;

    @Column(name = "photoUrl")
    private String photoUrl;


    public User() {
        //System.out.println("user init");
    }

}
