package dev.chermenin.dao;

import dev.chermenin.model.impl.GroupInformation;
import dev.chermenin.model.impl.Subject;
import dev.chermenin.model.impl.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GroupInformationRepository extends JpaRepository<GroupInformation, Long> {
    List<GroupInformation> findAllBySubjects(Set<Subject> subjects);
}
