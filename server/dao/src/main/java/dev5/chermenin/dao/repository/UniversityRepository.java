package dev5.chermenin.dao.repository;

import dev5.chermenin.model.entity.impl.University;
import dev5.chermenin.model.entity.impl.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University, Long> {

}

