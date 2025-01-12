package com.horizon.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.horizon.domain.Role;
import com.horizon.filters.JwtTokenFilter;
import com.horizon.response.ResponseObject;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@EnableWebMvc
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtTokenFilter jwtTokenFilter;

    @Value("${spring.application.api-prefix-home}")
    private String homeApiPrefix;

    @Value("${spring.application.api-prefix-admin}")
    private String adminApiPrefix;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exceptionHandling -> {
                    exceptionHandling
                            .accessDeniedHandler((request, response, accessDeniedException) -> {
                                ResponseObject<?> responseObject = new ResponseObject<>(
                                        HttpStatus.FORBIDDEN,
                                        "Forbidden",
                                        "Access Denied"
                                );
                                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                                response.setContentType("application/json");
                                ObjectMapper objectMapper = new ObjectMapper();
                                response.getWriter().write(objectMapper.writeValueAsString(responseObject));
                            })
                            .authenticationEntryPoint((request, response, authException) -> {
                                ResponseObject<?> responseObject = new ResponseObject<>(
                                        HttpStatus.UNAUTHORIZED,
                                        "Unauthorized",
                                        "You are not authenticated to access this resource"
                                );
                                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                                response.setContentType("application/json");
                                ObjectMapper objectMapper = new ObjectMapper();
                                response.getWriter().write(objectMapper.writeValueAsString(responseObject));
                            });
                })
                .authorizeHttpRequests(request -> {
                    request
                            .requestMatchers(homeApiPrefix + "/accounts/login").permitAll()
                            .requestMatchers(homeApiPrefix + "/accounts/register").permitAll()
                            .requestMatchers(homeApiPrefix + "/accounts/current-user").permitAll()
                            .requestMatchers(homeApiPrefix + "/accounts/logout").permitAll()
                            .requestMatchers(adminApiPrefix + "/**").hasRole(Role.ADMIN)
                            .requestMatchers(homeApiPrefix + "/**").hasRole(Role.USER)
                            .anyRequest().permitAll();
                });
        return http.build();
    }
}
