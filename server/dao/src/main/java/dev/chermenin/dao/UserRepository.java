package dev.chermenin.dao;

import dev.chermenin.dao.dto.Credentials;
import dev.chermenin.dao.dto.user.UserDto;
import dev.chermenin.dao.dto.user.UserProfileDto;
import dev.chermenin.model.impl.Group;
import dev.chermenin.model.impl.Subject;
import dev.chermenin.model.impl.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    boolean existsByUniversityIdAndId(Long universityId, Long userId);

    boolean existsByEmailOrNickname(String email, String nickname);

    boolean existsByEmailAndIdNot(String email, Long id);

    boolean existsByEmailOrNicknameAndIdNot(String email, String nickname, Long id);

    Page<User> findAllByGroupIdAndVerifiedTrue(Long groupId, Pageable pageable);

    Page<User> findAllByGroupIdAndVerifiedFalse(Long groupId, Pageable pageable);

    @Query(value = "select u.wishList from User u where u.id = :userId")
    List<Group> findAllInUserWishList(@Param("userId") Long userId);

    @Query(value = "select u from User u where u.id = :userId")
    @EntityGraph(value = "user.subjects")
    Optional<User> findAllUserSubjects(@Param("userId") Long userId);

    @Query(value = "select u.id, u.email, u.password, u.role from User u where u.id = :id")
    Optional<Credentials> getCredentialsById(@Param("id") Long id);

    @Query(value = "select u.id as id, u.email as email, u.password as password, u.role as role, u.enabled as enabled from User u where u.email = :id")
    Optional<Credentials> getCredentialsByEmail(@Param("id") String id);

    @EntityGraph(value = "user.allJoinsForId")
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndEnabledTrue(String email);

    @EntityGraph(value = "user.allJoinsForId")
    Optional<User> findUserByNickname(String nickname);

    @Override
    @EntityGraph(value = "user.allJoinsForId")
    Optional<User> findById(Long id);

    @Query(value = "select u.user from EmailVerificationToken u where u.token = :token")
    Optional<User> findUserByToken(@Param("token") String token);
}
