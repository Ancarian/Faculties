package dev5.chermenin.dao.repository;

import dev5.chermenin.model.entity.impl.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Ancarian on 14.11.2017.
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    @Query(value =
            "SELECT user.* FROM users user WHERE user.groups_id IS NOT NULL " +
                    "AND user.id NOT IN " +
                    "(SELECT USER_INFORMATION_ID FROM user_information_roles WHERE user.id = USER_INFORMATION_ID AND ROLES = 2)" +
                    "LIMIT 100"
            , nativeQuery = true)
    List<User> getRequests();

    @Query(value =
            "SELECT\n" +
                    "  user.*\n" +
                    "FROM USERS user\n" +
                    "  JOIN USERS_SUBJECTS S ON user.ID = S.USERS_ID\n" +
                    "WHERE user.GROUPS_ID = :group AND 1 IN" +
                    " (SELECT 1 FROM user_information_roles WHERE user.id = USER_INFORMATION_ID AND ROLES = 2)" +
                    " GROUP BY user.ID\n" +
                    " ORDER BY sum(S.MARK) desc", nativeQuery = true)
    List<User> getVerifiedUsersInGroup(@Param("group") long group);

    @Query(value =
            "SELECT\n" +
                    "  sum(S.MARK) AS score\n" +
                    "FROM USERS user\n" +
                    "  JOIN USERS_SUBJECTS S ON user.ID = S.USERS_ID\n" +
                    "WHERE user.GROUPS_ID = :group AND 1 IN" +
                    " (SELECT 1 FROM user_information_roles WHERE user.id = USER_INFORMATION_ID AND ROLES = 2)" +
                    " GROUP BY user.ID\n" +
                    " ORDER BY score desc", nativeQuery = true)
    List<BigInteger> getScoreUsersByGroup(@Param("group") long group);
}
