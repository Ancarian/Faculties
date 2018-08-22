package dev.chermenin.dao;


import dev.chermenin.model.impl.EmailVerificationToken;
import dev.chermenin.model.impl.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TokenVerificationRepository extends JpaRepository<EmailVerificationToken, Long> {

    Optional<EmailVerificationToken> findByToken(String token);

    Optional<EmailVerificationToken> findByUser(User user);

    Optional<EmailVerificationToken> findByUserId(Long id);

}
