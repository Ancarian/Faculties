package dev.chermenin.dao;

import dev.chermenin.model.impl.Group;
import dev.chermenin.model.impl.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findAllByGroupInformationId(Long id);

    List<Group> findAllByGroupInformationSubjects(Set<Subject> subjectSet);

    Optional<Group> findByIdAndGroupInformationSubjects(Long id, Set<Subject> subjectSet);

    Page<Group> findAllByGroupInformationFacultyId(Long id, Pageable pageable);

    @EntityGraph(value = "group.allJoinsForId")
    Optional<Group> findById(Long id);


}
