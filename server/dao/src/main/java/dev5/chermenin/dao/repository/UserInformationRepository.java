package dev5.chermenin.dao.repository;

import dev5.chermenin.model.entity.impl.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Ancarian on 14.11.2017.
 */

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {

    UserInformation findByEmail(@Param("email") String email);

    UserInformation findByNickname(@Param("nickname") String nickname);

}
