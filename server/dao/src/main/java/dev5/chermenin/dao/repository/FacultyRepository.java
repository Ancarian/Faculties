package dev5.chermenin.dao.repository;

import dev5.chermenin.model.entity.impl.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

}

