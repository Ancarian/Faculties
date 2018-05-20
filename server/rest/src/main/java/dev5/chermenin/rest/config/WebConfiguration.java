package dev5.chermenin.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Ancarian on 18.02.2018.
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    private static final int MAX_AGE = 3600;

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("PUT", "DELETE", "GET", "POST", "OPTIONS")
                .allowCredentials(false).maxAge(MAX_AGE);
    }
}