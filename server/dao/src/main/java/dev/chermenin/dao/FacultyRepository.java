package dev.chermenin.dao;

import dev.chermenin.dao.dto.faculty.FacultyDto;
import dev.chermenin.model.impl.Faculty;
import dev.chermenin.model.impl.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends CrudRepository<Faculty, Long> {
    @EntityGraph(value = "faculty.university")
    Page<Faculty> findAllByUniversityId(Long id, Pageable pageable);

    @EntityGraph(value = "faculty.university")
    Optional<Faculty> findById(Long id);

    @EntityGraph(value = "faculty.university")
    Page<Faculty> findAll(Pageable pageable);
}
