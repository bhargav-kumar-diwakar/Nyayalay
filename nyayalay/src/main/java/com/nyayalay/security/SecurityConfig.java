package com.nyayalay.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/cases/**")
                        .hasAnyRole("ADMIN","JUDGE","CLERK","ATTORNEY")
                        .requestMatchers(HttpMethod.POST,"/api/cases/**")
                        .hasAnyRole("ADMIN","CLERK")
                        .requestMatchers(HttpMethod.PUT,"/api/cases/**")
                        .hasAnyRole("ADMIN","CLERK")
                        .requestMatchers(HttpMethod.DELETE,"/api/cases/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,"/api/hearings/**")
                        .hasAnyRole("ADMIN","JUDGE","CLERK","ATTORNEY")
                        .requestMatchers(HttpMethod.POST,"/api/hearings/**")
                        .hasAnyRole("ADMIN","CLERK")
                        .requestMatchers(HttpMethod.PUT, "/api/hearings/**")
                        .hasAnyRole("ADMIN","JUDGE","CLERK")
                        .requestMatchers(HttpMethod.DELETE,"/api/hearings/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,"/api/parties/**")
                        .hasAnyRole("ADMIN","JUDGE","CLERK","ATTORNEY")
                        .requestMatchers(HttpMethod.POST,"/api/parties/**")
                        .hasAnyRole("ADMIN","CLERK")
                        .requestMatchers(HttpMethod.PUT,"/api/parties/**")
                        .hasAnyRole("ADMIN","CLERK")
                        .requestMatchers(HttpMethod.DELETE,"/api/parties/**")
                        .hasRole("ADMIN")

                        .requestMatchers(HttpMethod.GET,"/api/documents/**")
                        .hasAnyRole("ADMIN","JUDGE","CLERK","ATTORNEY")
                        .requestMatchers(HttpMethod.POST,"/api/documents/**")
                        .hasAnyRole("ADMIN","CLERK")
                        .requestMatchers(HttpMethod.DELETE,"/api/documents/**")
                        .hasRole("ADMIN")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );
        return http.build();
    }

}
