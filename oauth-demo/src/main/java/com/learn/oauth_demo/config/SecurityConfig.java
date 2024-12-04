package com.learn.oauth_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/login**").permitAll()
                .anyRequest().authenticated()                // Secure all other endpoints
            )
            .oauth2Login(oauth -> oauth
                .defaultSuccessUrl("/loginSuccess", true)    // Redirect after successful login
            );

        return http.build();
    }
}
