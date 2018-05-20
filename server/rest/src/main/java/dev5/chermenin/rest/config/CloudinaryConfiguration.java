package dev5.chermenin.rest.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfiguration {

    @Value("${cloudinary.environment.variable}")
    private String envVariable;

    @Bean
    public Cloudinary cloudinary(){
        return  new Cloudinary(envVariable);
    }

}
