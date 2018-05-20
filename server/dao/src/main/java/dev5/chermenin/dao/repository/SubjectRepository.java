package dev5.chermenin.dao.repository;

import dev5.chermenin.model.entity.impl.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by Ancarian on 01.12.2017.
 */

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    Subject findBySubject(String subject);

    List<Subject> findAllBySubjectLike(String subject);

    @Query(value =
            "SELECT subject.*, users_subjects.MARK FROM SUBJECTS subject\n" +
                    "        JOIN users_subjects ON SUBJECTS_ID = subject.id WHERE users_id = :userId", nativeQuery = true)
    Set<Subject> getSubjectByUser(@Param("userId") long id);

    @Query(value =
            "SELECT subject.* FROM SUBJECTS subject\n" +
                    "        JOIN GROUPS_SUBJECTS ON SUBJECTS_ID = subject.id WHERE GROUPS_ID = :groupId", nativeQuery = true)
    Set<Subject> getSubjectByGroup(@Param("groupId") long groupId);

    @Query(value =
            "select subj from Subject subj where subj.id in :ids")
    List<Subject> findByInventoryIds(@Param("ids") List<Long> inventoryIdList);

}
