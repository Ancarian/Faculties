package dev.chermenin.dao;

import dev.chermenin.dao.dto.Credentials;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void getCredentialsById() {
        userRepository.getCredentialsById(1L);
    }

    @Test
    public void getCredentialsByEmail() {
        Credentials credentials = userRepository.getCredentialsByEmail("email").get();

        System.out.println(credentials.getEmail());
    }
}