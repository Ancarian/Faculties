package dev5.chermenin.rest.config;

import dev5.chermenin.rest.security.handler.RestAccessDeniedHandler;
import dev5.chermenin.rest.security.handler.RestAuthenticationEntryPoint;
import dev5.chermenin.rest.security.service.JwtAuthenticationFilter;
import dev5.chermenin.rest.security.service.JwtAuthenticationProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Created by Ancarian on 20.12.2017.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@AllArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private static final String[] AUTH_POST_RESPONSE_WHITELIST = new String[]{"/auth/login", "/auth/sign_up"};

    private static final String[] AUTH_SWAGGER_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
    };

    private static final String[] AUTH_GET_RESPONSE_WHITELIST = {
            "/groups/**",
            "/subjects/**",
            "/faculties/**",
            "/universities/**"
    };

    private final UserDetailsService userDetailsService;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public void configureAuthentication(final AuthenticationManagerBuilder authenticationManagerBuilder)
            throws Exception {
        authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .authenticationProvider(this.jwtAuthenticationProvider)
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().

                sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(AUTH_SWAGGER_WHITELIST)
                .permitAll()
                .and()
                .csrf().disable()
                .addFilterAfter(new JwtAuthenticationFilter(authenticationManagerBean()),
                        BasicAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .accessDeniedHandler(new RestAccessDeniedHandler());
    }

    @Override
    public void configure(final WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.POST, AUTH_POST_RESPONSE_WHITELIST)
                .antMatchers(AUTH_SWAGGER_WHITELIST)
                .antMatchers(HttpMethod.GET, AUTH_GET_RESPONSE_WHITELIST)
                .antMatchers(HttpMethod.OPTIONS, "/**");
    }

}
