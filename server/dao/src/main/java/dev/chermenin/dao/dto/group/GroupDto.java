package dev.chermenin.dao.dto.group;

import dev.chermenin.dao.dto.Dto;
import dev.chermenin.model.impl.Group;
import dev.chermenin.model.impl.GroupInformation;
import dev.chermenin.model.impl.User;
import dev.chermenin.model.impl.enums.GroupType;
import dev.chermenin.model.impl.enums.TimeType;
import lombok.Value;

import java.sql.Date;
import java.util.List;


@Value
public class GroupDto implements Dto {
    private Long id;
    private GroupType groupType;
    private TimeType timeType;
    private Date issueDate;
    private Date validTill;
    private int limit;
    private int enrollMark;
    private GroupInformationDto groupInformation;

    public GroupDto(Group group) {
        this.id = group.getId();
        this.groupType = group.getGroupType();
        this.timeType = group.getTimeType();
        this.issueDate = group.getIssueDate();
        this.validTill = group.getValidTill();
        this.limit = group.getLimit();
        this.enrollMark = group.getEnrollMark();
        this.groupInformation = new GroupInformationDto(group.getGroupInformation());
    }
}
