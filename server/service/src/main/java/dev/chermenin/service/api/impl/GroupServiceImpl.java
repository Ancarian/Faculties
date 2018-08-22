package dev.chermenin.service.api.impl;

import dev.chermenin.dao.GroupRepository;
import dev.chermenin.dao.UserRepository;
import dev.chermenin.dao.dto.create.FacultyRegistrationDto;
import dev.chermenin.dao.dto.create.GroupRegistrationDto;
import dev.chermenin.dao.dto.group.GroupDto;
import dev.chermenin.model.impl.Group;
import dev.chermenin.service.api.GroupService;
import dev.chermenin.service.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Override
    public GroupDto findById(Long id) {
        return new GroupDto(groupRepository.findById(id).orElseThrow(
                () -> new NotFoundException("error.group.notExistsId")
        ));
    }

    @Override
    public Page<GroupDto> findAllByFacultyId(Pageable pageable, Long id) {
        return groupRepository.findAllByGroupInformationFacultyId(id, pageable).map(GroupDto::new);
    }

    @Override
    public Page<GroupDto> findAll(Pageable pageable) {
        return groupRepository.findAll(pageable).map(GroupDto::new);
    }

    @Transactional
    @Override
    public void addToWishList(Long userId, Long groupId) {
        List<Group> groups = userRepository.findAllInUserWishList(userId);
        Group group = new Group();
        group.setId(groupId);
        groups.add(group);
    }

    @Override
    public Page<GroupDto> findAll(Pageable pageable, Specification<GroupDto> specification) {
        return null;
    }

    @Override
    public List<GroupDto> findById(Iterable<Long> ids) {
        return groupRepository.findAllById(ids).stream().map(GroupDto::new).collect(Collectors.toList());
    }

    @Override
    public void remove(Long id) {
        if (!groupRepository.existsById(id)){
            throw new NotFoundException("error.group.notExistsId");
        }
    }

    @Override
    public GroupRegistrationDto save(GroupRegistrationDto dto) {
        Group group = new Group();
        return null;
    }

    @Override
    public GroupRegistrationDto update(GroupRegistrationDto dto) {
        return null;
    }
}
