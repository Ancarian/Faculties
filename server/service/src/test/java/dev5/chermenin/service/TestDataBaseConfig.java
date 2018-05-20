package dev5.chermenin.service;

import com.cloudinary.Cloudinary;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import java.util.Properties;

/**
 * Created by Ancarian on 15.11.2017.
 */

import java.util.Properties;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "dev5.chermenin")
@EntityScan(basePackages = "dev5.chermenin.model.entity.impl")
@EnableJpaRepositories(basePackages = "dev5.chermenin.dao.repository")
@TestPropertySource("classpath:application-test.yml")
public class TestDataBaseConfig {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("facultiesgit@gmail.com");
        mailSender.setPassword("z200198l192ws");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");

        return mailSender;
    }

    @Bean
    public Cloudinary cloudinary(){
        return  new Cloudinary("cloudinary://472951833181896:PNFHnk-FdjEfHgxR4Eu5ce39p7U@bravewall/");
    }

    @Bean
    public SimpleMailMessage mailMessage(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("test");
        return message;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

