package dev.chermenin.model.impl;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "faculties")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "faculty.university", attributeNodes = {
                @NamedAttributeNode("university"),
        })
})
public class Faculty extends BaseObject {
    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = false, length = 20)
    private String phone;

    @Column(unique = true, nullable = false, length = 30)
    private String name;

    @Column(unique = true, nullable = false, length = 8)
    private String shortName;

    @Column(nullable = false, length = 512)
    private String information;

    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY)
    private List<GroupInformation> groupInformation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "universities_id", nullable = false)
    private University university;
}
