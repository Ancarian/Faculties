package dev5.chermenin.dao.repository;

import dev5.chermenin.model.entity.impl.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Created by Ancarian on 14.11.2017.
 */

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findByInformationLike(@Param("info") String info);

    @Modifying
    @Query(value = "UPDATE groups AS gr SET gr.FACULTIES_ID = :facultiesId WHERE gr.id = :groupId", nativeQuery = true)
    void updateFaculty(@Param("groupId") long groupId, @Param("facultiesId") long facultiesId);

    List<Group> findAllByValidTillEquals(@Param("date") Date date);
}
