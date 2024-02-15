package com.peakosoft.giftlistj7.config;

import com.peakosoft.giftlistj7.config.jwt.JwtFilter;
import com.peakosoft.giftlistj7.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final JwtFilter jwtFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserServiceImpl();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return new ProviderManager(provider);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/api/oauth2/with-google",
                                    "/api/auth/sign-up",
                                    "/api/auth/sign-in",
                                    "/api/auth/forgot-password",
                                    "/api/auth/change-password").permitAll()
                            .requestMatchers("/api/holiday/**").hasAnyAuthority("ADMIN", "USER")
                            .requestMatchers("/api/wish_lists/**").hasAnyAuthority("ADMIN", "USER")
                            .requestMatchers("/api/friends/**").hasAnyAuthority("ADMIN", "USER")
                            .requestMatchers("/api/holiday/**").hasAnyAuthority("ADMIN","USER")
                            .requestMatchers("/api/wish_lists/**").hasAnyAuthority("ADMIN","USER")
                            .requestMatchers("/api/friends/**").hasAnyAuthority("ADMIN","USER")
                            .requestMatchers("/api/charity/**").hasAnyAuthority("ADMIN","USER")
                            .requestMatchers("/swagger-ui/**",
                                    "/swagger-resources/*," +
                                            "/v3/api-docs/**").permitAll()
                            .requestMatchers("/api/complaints/save").hasAnyAuthority("ADMIN","USER")
                            .requestMatchers("/api/complaints/find-all", "/api/complaints/find-by-id", "/api/complaints/delete").hasAuthority("ADMIN")
                            .requestMatchers("/api/notifications/**").hasAnyAuthority("ADMIN","USER")
                            .anyRequest().authenticated();
                })
                .oauth2Login(withDefaults())
                .formLogin(withDefaults())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
