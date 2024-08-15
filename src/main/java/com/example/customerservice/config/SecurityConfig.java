package com.example.customerservice.config;


import com.example.customerservice.exception.BusinessException;
import com.example.customerservice.exception.constant.ErrorCode;
import com.example.customerservice.repository.CustomerRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;

@Configuration(proxyBeanMethods = false)
public class SecurityConfig {
    private final CustomerRepository customerRepository;

    public SecurityConfig(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/", "/customers").permitAll()
                        .anyRequest()
                        .authenticated()
                )
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(
                        jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
                ))
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            String jwtId = jwt.getClaimAsString("sub");
            try {
                customerRepository.findByJwtId(jwtId).orElseThrow(
                        () -> new BusinessException(ErrorCode.JWT_ID_NOT_MATCH));

                return Collections.emptyList();
            } catch (BusinessException ex) {
                throw new RuntimeException("BusinessException occurred while processing JWT ID: " + ex.getMessage());
            } catch (Exception ex) {
                throw new RuntimeException("Error occurred while processing JWT ID: " + ex.getMessage());
            }
        });
        return converter;
    }


}
