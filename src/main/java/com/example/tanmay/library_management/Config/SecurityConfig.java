package com.example.tanmay.library_management.Config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.tanmay.library_management.Service.UserAuthService;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    
    @Autowired
    private UserAuthService userAuthService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        System.out.println("in security");
        http.csrf(customizer->customizer.disable());
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        http.userDetailsService(userAuthService);
        http.httpBasic(withDefaults());

        return http.build();
    }
}
