package dev.chermenin.service.api;


import dev.chermenin.dao.dto.create.FacultyRegistrationDto;
import dev.chermenin.dao.dto.create.GroupRegistrationDto;
import dev.chermenin.dao.dto.faculty.FacultyDto;
import dev.chermenin.dao.dto.group.GroupDto;
import dev.chermenin.model.impl.Faculty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface GroupService{
    GroupDto findById(Long id);

    Page<GroupDto> findAllByFacultyId(Pageable pageable, Long id);

    Page<GroupDto> findAll(Pageable pageable);

    void addToWishList(Long userId, Long groupId);

    Page<GroupDto> findAll(Pageable pageable, Specification<GroupDto> specification);

    List<GroupDto> findById(Iterable<Long> ids);

    void remove(Long id);

    GroupRegistrationDto save(GroupRegistrationDto id);

    GroupRegistrationDto update(GroupRegistrationDto id);
}

