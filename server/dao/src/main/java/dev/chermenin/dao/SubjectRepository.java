package dev.chermenin.dao;

import dev.chermenin.model.impl.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    @Query(value = "select s from SUBJECTS s " +
            "join groups_subjects on groups_subjects.SUBJECTS_ID = s.id and groups_id = :id",
            nativeQuery = true)
    Set<Subject> findAllByGroupId(@Param("id") Long id);


    boolean existsAllById(List<Long> id);
}
