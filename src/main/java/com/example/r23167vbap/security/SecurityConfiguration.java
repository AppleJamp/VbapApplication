package com.example.r23167vbap.security;

import com.example.r23167vbap.model.enums.Role;
import com.example.r23167vbap.security.filter.JwtAccessFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Component
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfiguration {
    private final JwtAccessFilter jwtAccessFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Value("${web.cors.allowed-origins}")
    private List<String> allowedOrigins;

    @Value("${web.cors.allowed-methods}")
    private List<String> allowedMethods;

    @Value("${web.cors.allowed-headers}")
    private List<String> allowedHeaders;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.exceptionHandling(exceptionHandlingConfigurer -> exceptionHandlingConfigurer.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.headers(httpSecurityHeadersConfigurer -> {
            httpSecurityHeadersConfigurer.httpStrictTransportSecurity(HeadersConfigurer.HstsConfig::disable);
        });


        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        request -> request.requestMatchers("/kniha", "/autor").hasRole(Role.USER.name())
                                .requestMatchers(HttpMethod.GET, "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/uzivatel/authenticate", "/uzivatel/registration").permitAll()
                                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                                .requestMatchers("/**").hasAnyRole(Role.USER.name())
                                .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAccessFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public CorsFilter corsFilter() {
        var source = new UrlBasedCorsConfigurationSource();
        var config = new CorsConfiguration();

        log.info("CORS allowed origins: {}.", allowedOrigins);
        log.info("CORS allowed methods: {}.", allowedMethods);
        log.info("CORS allowed headers: {}.", allowedHeaders);

        config.setAllowedOrigins(allowedOrigins);
        config.setAllowedMethods(allowedMethods);
        config.setAllowedHeaders(allowedHeaders);

        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
