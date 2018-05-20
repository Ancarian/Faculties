package dev5.chermenin.service.impl;

import dev5.chermenin.dao.repository.FacultyRepository;
import dev5.chermenin.dao.repository.GroupRepository;
import dev5.chermenin.dao.repository.SubjectRepository;
import dev5.chermenin.dao.repository.UserRepository;
import dev5.chermenin.model.entity.impl.Group;
import dev5.chermenin.model.entity.impl.enums.Roles;
import dev5.chermenin.service.api.GroupService;
import dev5.chermenin.service.dto.impl.GroupDto;
import dev5.chermenin.service.dto.impl.subject.SubjectDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;
    private final FacultyRepository facultyRepository;
    private final SubjectRepository subjectRepository;

    @Transactional(readOnly = true)
    public GroupDto findById(Long id) {
        Group group = groupRepository.findOne(id);
        GroupDto groupDto = new GroupDto(group);

        List<BigInteger> scores = userRepository.getScoreUsersByGroup(id);
        groupDto.setUsers(scores);
        groupDto.setEnrollMark(scores.size() > group.getLimit() ? scores.get(group.getLimit() - 1) : null);

        return groupDto;
    }

    @Transactional
    public GroupDto save(GroupDto groupDto) {
        groupDto.setId(null);
        Group group = new Group();
        group.setInformation(groupDto.getInformation());
        group.setLimit(groupDto.getLimit());
        group.setValidTill(groupDto.getValidTill());
        group.setIssueDate(groupDto.getIssueDate());
        group.setUsers(null);
        group.setQualify(groupDto.getQualify());
        group.setFaculty(facultyRepository.findOne(groupDto.getFacultyComponentDto().getId()));
        group.setSubjects(subjectRepository.findByInventoryIds(groupDto.getSubjects()
                .stream()
                .map(SubjectDto::getId)
                .collect(Collectors.toList())));

        groupDto = new GroupDto(groupRepository.save(group));
        return groupDto;
    }

    @Transactional
    public void remove(Long id) {
        groupRepository.delete(id);
    }

    @Transactional
    public void update(GroupDto groupDto) {
        Group group = groupRepository.findOne(groupDto.getId());
        group.setInformation(groupDto.getInformation());
        group.setLimit(groupDto.getLimit());
        group.setValidTill(groupDto.getValidTill());
        group.setIssueDate(groupDto.getIssueDate());
        group.setQualify(groupDto.getQualify());
        group.setSubjects(subjectRepository.findByInventoryIds(groupDto.getSubjects().stream().map(SubjectDto::getId).collect(Collectors.toList())));

        group.getUsers().forEach(user -> {
            user.getInfo().getRoles().remove(Roles.VERIFIED);
            user.setGroup(null);
        });
        group.setUsers(null);
        groupRepository.save(group);
    }

    @Transactional(readOnly = true)
    public List<GroupDto> findAll(Pageable pageable) {
        List<Group> groups = groupRepository.findAll(pageable).getContent();
        groups.forEach(group -> group.setUsers(null));
        return groups.stream()
                .map(GroupDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void removeAll() {
        groupRepository.deleteAll();
    }
}

