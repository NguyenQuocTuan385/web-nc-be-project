package com.group6.ads.configs;

import com.group6.ads.enums.ERoles;
import com.group6.ads.filters.JwtTokenFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;

    @Value("${api.prefix}")
    private String apiPrefix;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .cors(corConf -> corConf.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)

                .authorizeHttpRequests(requests -> {
                    requests
                            .requestMatchers(
                                    String.format("%s/authentication/register", apiPrefix),
                                    String.format("%s/authentication/refresh", apiPrefix),
                                    String.format("%s/authentication/login", apiPrefix),
                                    String.format("%s/authentication/logout", apiPrefix),
                                    String.format("%s/authentication/change-password", apiPrefix),
                                    String.format("%s/authentication/reset-password", apiPrefix),
                                    String.format("%s/locations", apiPrefix),
                                    String.format("%s/locations/**", apiPrefix),
                                    String.format("%s/locations-client/**", apiPrefix),
                                    String.format("%s/locations-client", apiPrefix),
                                    String.format("%s/reports", apiPrefix),
                                    String.format("%s/reports/**", apiPrefix),
                                    String.format("%s/advertises", apiPrefix),
                                    String.format("%s/advertises/**", apiPrefix),
                                    String.format("%s/advertises/*/contracts", apiPrefix),
                                    String.format("%s/advertises-client/**", apiPrefix),
                                    String.format("%s/advertises-client", apiPrefix),
                                    String.format("%s/reports-client", apiPrefix),
                                    String.format("%s/reports-client/**", apiPrefix),
                                    String.format("%s/advertise-forms", apiPrefix),
                                    String.format("%s/contracts", apiPrefix),
                                    String.format("%s/contracts/**", apiPrefix),
                                    String.format("%s/contracts/advertises/**", apiPrefix),
                                    String.format("%s/report-forms/**", apiPrefix),
                                    String.format("%s/locations/*/advertises", apiPrefix),
                                    String.format("%s/report-forms", apiPrefix),
                                    String.format("%s/email/**", apiPrefix),
                                    String.format("%s/users/**", apiPrefix),
                                    String.format("%s/properties/**", apiPrefix),

                                    "/v3/api-docs/**",
                                    "/swagger-ui/**",
                                    "/swagger-ui.html"

                            )
                            .permitAll()
                            .anyRequest()
                            .hasAnyRole(
                                    ERoles.WARD.toString(),
                                    ERoles.DEPARTMENT.toString(),
                                    ERoles.DISTRICT.toString());
                });
        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        //the below three lines will add the relevant CORS response headers
        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
