package org.example;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

import static org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/error", "/webjars/**", "/oauth2/**").permitAll()
                        .anyRequest().authenticated()
                )
                .exceptionHandling(exception -> exception
                        .accessDeniedPage("/error")
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/?logout").permitAll()
                )
                .oauth2Login(oauth2 -> oauth2
                        .defaultSuccessUrl("/")
                        .failureUrl("/?error")
                );

        return http.build();
    }
}