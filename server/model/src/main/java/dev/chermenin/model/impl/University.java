package dev.chermenin.model.impl;

import dev.chermenin.model.impl.adress.City;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "universities")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "university.withCity", attributeNodes = {
                @NamedAttributeNode("city"),
        })
})
public class University extends BaseObject {
    @Column(unique = true, nullable = false, length = 30)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false, length = 20)
    private String phone;

    @Column(unique = true, nullable = false, length = 8)
    private String shortName;

    @Column(nullable = false, length = 512)
    private String information;

    @OneToMany(mappedBy = "university", fetch = FetchType.LAZY)
    private Set<Faculty> faculties;

    @Column(nullable = false, length = 20)
    private String adress;

    @ManyToOne(optional = false)
    private City city;
}
