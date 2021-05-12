package com.demoservice.configuration;

import com.demoservice.constants.DBConstants;
import com.demoservice.entity.User;
import com.demoservice.service.common.LoggingService;
import com.demoservice.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private Environment env;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private UserService userService;
    @Autowired
    private LoggingService loggingService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .headers().frameOptions().sameOrigin()
            .and()
            .authorizeRequests()
            .antMatchers(DBConstants.H2_CONSOLE).hasRole(DBConstants.ROLE_ADMIN)
            .antMatchers("/actuator/**").permitAll()
            .antMatchers("/swagger-ui/**").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .httpBasic()
            .authenticationEntryPoint(authenticationEntryPoint);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) {
        List<User> users = userService.getUsers();
        users.stream().forEach(user -> {
            try {
                auth.inMemoryAuthentication()
                        .withUser(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getRole());
            } catch (Exception e) {
                loggingService.logError(e);
            }
        });
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
