package com.siddu.springsecurity.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.siddu.springsecurity.configuration.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .antMatchers("/management/api/**").hasAnyRole(ADMIN.name(), GA.name())
                .anyRequest()
                .authenticated().and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {

        UserDetails adminUser = User.builder().username("admin")
                .password(passwordEncoder.encode("adminPwd"))
                .authorities(ADMIN.getGrantedAuthorities())
//                .roles(ADMIN.name())
                .build();

        UserDetails ga = User.builder().username("ga")
                .password(passwordEncoder.encode("gaPwd"))
                .authorities(GA.getGrantedAuthorities())
//                .roles(GA.name())
                .build();

        UserDetails sidStudentUser = User.builder()
                .username("siddu")
                .password(passwordEncoder.encode("asdf10"))
                .authorities(STUDENT.getGrantedAuthorities())
//                .roles(STUDENT.name())
                .build();

        return new InMemoryUserDetailsManager(adminUser, sidStudentUser, ga);
    }
}
