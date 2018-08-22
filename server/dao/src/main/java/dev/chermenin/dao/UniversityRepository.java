package dev.chermenin.dao;

import dev.chermenin.dao.dto.university.UniversityDto;
import dev.chermenin.model.impl.University;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UniversityRepository extends JpaRepository<University, Long>, JpaSpecificationExecutor<University> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM University u " +
            " WHERE (u.email = :email or u.phone = :phone or u.name = :name or u.shortName = :shortName) " +
            "  and u.id <> :id ")
    boolean existsByEmailOrPhoneOrNameOrShortNameAndIdNot(@Param("email") String email,
                                                          @Param("phone") String phone,
                                                          @Param("name") String name,
                                                          @Param("shortName") String shortName,
                                                          @Param("id") Long id);

    boolean existsByEmailOrPhoneOrNameOrShortName(String email, String phone, String name, String shortName);

    @EntityGraph(value = "university.withCity")
    Page<University> findAllBy(Pageable pageable);

    @EntityGraph(value = "university.withCity")
    Page<University> findAllBy(Pageable pageable, Specification<UniversityDto> specification);
}
