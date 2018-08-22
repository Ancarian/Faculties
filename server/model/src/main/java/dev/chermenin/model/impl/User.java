package dev.chermenin.model.impl;

import dev.chermenin.model.impl.adress.City;
import dev.chermenin.model.impl.enums.Roles;
import dev.chermenin.model.impl.enums.Sex;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@Table(name = "users")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "user.allJoinsForId", attributeNodes = {
                @NamedAttributeNode("city"),
                @NamedAttributeNode("subjects"),
                @NamedAttributeNode(value = "group", subgraph = "user.group.info"),
                @NamedAttributeNode("university"),
                @NamedAttributeNode("wishList")
        }, subgraphs = {
                @NamedSubgraph(
                        name = "user.group.info",
                        attributeNodes = {
                                @NamedAttributeNode(value = "groupInformation")})
        }),
        @NamedEntityGraph(name = "user.subjects", attributeNodes = {
                @NamedAttributeNode("subjects")})
})
public class User extends BaseObject {
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "lastname", nullable = false, length = 20)
    private String lastname;

    @Column(name = "patronymic", nullable = false, length = 20)
    private String patronymic;

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Group> wishList;

    @ManyToOne(fetch = FetchType.LAZY)
    private University university;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "users_subjects", joinColumns = {@JoinColumn(name = "users_id")})
    @MapKeyJoinColumn(name = "subjects_id")
    @Column(name = "mark")
    private Map<Subject, Integer> subjects;

    @Column(name = "photoUrl")
    private String photoUrl;

    @Column(name = "enabled")
    private boolean enabled;

    @Column(name = "verified")
    private boolean verified;

    @Column(name = "date_of_registration")
    private Date dateOfRegistration;

    @Column(name = "nickname", nullable = false, unique = true, length = 20)
    private String nickname;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "smallint")
    private Roles role;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "smallint")
    private Sex sex;

    @Column(nullable = false, length = 20)
    private String adress;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private City city;
}
