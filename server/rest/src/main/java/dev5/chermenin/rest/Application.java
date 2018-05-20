package dev5.chermenin.rest;

import dev5.chermenin.dao.repository.UserRepository;
import dev5.chermenin.model.entity.impl.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

@SpringBootApplication
@ComponentScan(basePackages = {"dev5.chermenin"})
@EnableTransactionManagement
@EntityScan(basePackages = {"dev5.chermenin.model.entity.impl"})
@EnableJpaRepositories(basePackages = {"dev5.chermenin.dao.repository"})
@EnableWebMvc
@EnableAsync
@EnableScheduling
public class Application extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, (String[]) args);
    }

    @Bean
    int bootstrap(final UserRepository userRepository, final PasswordEncoder passwordEncoder) {
        List<User> users = userRepository.findAll();
        if (users.get(0).getInfo().getPassword().contains("password")){
            users.forEach(o -> o.getInfo().setPassword(passwordEncoder.encode(o.getInfo().getPassword())));
            userRepository.save(users);
        }
        return 1;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new StandardPasswordEncoder();

    }

    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
