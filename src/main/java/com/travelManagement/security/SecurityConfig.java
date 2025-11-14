package com.travelManagement.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // disable CSRF for APIs
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/login", "/api/users/register").permitAll() // public endpoints
                .anyRequest().authenticated() // everything else needs auth
            )
            .formLogin(login -> login.disable()) // disable default login page
            .httpBasic(basic -> basic.disable()); // disable basic auth

        return http.build();
    }
}
