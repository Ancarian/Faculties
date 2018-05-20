package dev5.chermenin.service.impl;

import dev5.chermenin.model.entity.impl.Faculty;
import dev5.chermenin.model.entity.impl.Group;
import dev5.chermenin.service.TestDataBaseConfig;
import dev5.chermenin.service.api.GroupService;
import dev5.chermenin.service.dto.impl.GroupDto;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ancarian on 10.11.2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestDataBaseConfig.class)
public class GroupServiceTest {
    private final static Pageable pageable = new PageRequest(0, 100);
    @Autowired
    private GroupService groupService;

    @Test
    public void findById() {
        Long value = 1L;
        System.out.println(groupService.findById(1L).getSubjects());
        assertEquals(groupService.findById(1L).getId(), value);
    }

    @Test
    public void saveGroup() {
        Group group = new Group();
        group.setInformation("group №4");
        group.setUsers(new ArrayList<>());
        group.setSubjects(new ArrayList<>());
        group.setFaculty(new Faculty());

        group.getFaculty().setId(1L);
        group.setQualify("builder");
        group.getFaculty().setId(1L);
        groupService.save(new GroupDto(group));
        group.setId(4L);
        assertEquals(group.getInformation(), groupService.findById(4L).getInformation());

        groupService.remove(4L);
    }

    @Test()
    public void updateGroup() {
        GroupDto groupDto = groupService.findById(1L);
        groupDto.setInformation("newGroup#1");
        groupService.update(groupDto);
        assertEquals(groupService.findById(1L).getInformation(), groupDto.getInformation());
    }

    @Ignore
    @Test
    public void removeGroup() {
        groupService.remove(1L);
    }

    @Test
    public void findAll() {
        assertEquals(groupService.findAll(pageable).size(), 3);
    }

}
