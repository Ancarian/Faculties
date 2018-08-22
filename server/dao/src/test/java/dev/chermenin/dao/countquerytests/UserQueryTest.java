package dev.chermenin.dao.countquerytests;

import dev.chermenin.dao.TestConfiguration;
import dev.chermenin.dao.UserRepository;
import dev.chermenin.dao.dto.Credentials;
import dev.chermenin.dao.dto.user.UserDto;
import dev.chermenin.dao.util.SpecsBuilder;
import dev.chermenin.model.impl.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import static com.vladmihalcea.sql.SQLStatementCountValidator.assertSelectCount;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class UserQueryTest extends CountQueryTest {
    private final Pageable pageable = PageRequest.of(0, 10);
    @Autowired
    private UserRepository userRepository;

    @Test
    public void findById() {
        userRepository.findById(1L);
        assertSelectCount(1);
    }

    @Test
    public void getCredentialsFindById() {
        userRepository.findById(1L);
        assertSelectCount(1);
    }

    @Test
    public void getCredentialsByEmailOrNicknameTest() {
        userRepository.findByEmail("email");
        assertSelectCount(1);
    }

    @Test
    public void findAllByGroupIdAndVerifiedTrueTest() {
        userRepository.findAllByGroupIdAndVerifiedTrue(1L, pageable);
        assertSelectCount(1);
    }

    @Test
    public void findAllByGroupIdAndVerifiedFalseTest() {
        userRepository.findAllByGroupIdAndVerifiedFalse(1L, pageable);
        assertSelectCount(1);
    }

    @Test
    public void findAllTest() {
        userRepository.findAll(pageable);
        assertSelectCount(1);
    }

    @Test
    public void findAllWithSpecTest() {
        String search = "enrollMark_group:5,limit_group:32";
        Specification<User> groupSpecification2 = new SpecsBuilder<User>().createSpecification(search);
        userRepository.findAll(groupSpecification2, pageable);
        assertSelectCount(1);
    }
}
