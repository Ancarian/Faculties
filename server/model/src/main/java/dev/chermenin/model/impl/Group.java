package dev.chermenin.model.impl;

import dev.chermenin.model.impl.enums.GroupType;
import dev.chermenin.model.impl.enums.TimeType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NamedEntityGraphs({
        @NamedEntityGraph(name = "group.allJoinsForId", attributeNodes = {
                @NamedAttributeNode(value = "groupInformation", subgraph = "group.groupInformation.info"),
        }, subgraphs = {
                @NamedSubgraph(
                        name = "group.groupInformation.info",
                        attributeNodes = {
                                @NamedAttributeNode(value = "subjects")})
        })
})
public class Group extends BaseObject {
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "smallint")
    private GroupType groupType;

    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "smallint")
    private TimeType timeType;

    @Column(name = "issue_date")
    private Date issueDate;

    @Column(name = "valid_till")
    private Date validTill;

    @Column(name = "count", nullable = false)
    private int limit;

    @Column(name = "enroll_mark")
    private int enrollMark;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY)
    private List<User> users;

    @ManyToOne(fetch = FetchType.LAZY)
    private GroupInformation groupInformation;
}
