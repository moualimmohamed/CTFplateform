package com.ctf.v1.config;

import static com.ctf.v1.model.Permission.*;
import static com.ctf.v1.model.Permission.PLAYER_DELETE;
import static com.ctf.v1.model.Role.ADMIN;
import static com.ctf.v1.model.Role.PLAYER;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import com.ctf.v1.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
        private final JwtAuthenticationFilter jwtAuthenticationFilter;
        private final UserService userService;

        private static final String[] WHITE_LIST_URL = { "/api/auth/**",
                        "/api/home" };

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .csrf(AbstractHttpConfigurer::disable)
                                .authorizeHttpRequests(req -> req.requestMatchers(WHITE_LIST_URL)
                                                .permitAll()
                                                .requestMatchers("/api/admins/**")
                                                .hasAnyRole(ADMIN.name(), PLAYER.name())
                                                .requestMatchers(GET, "/api/admins/**")
                                                .hasAnyAuthority(ADMIN_READ.name(), PLAYER_READ.name())
                                                .requestMatchers(POST, "/api/admins/**")
                                                .hasAnyAuthority(ADMIN_CREATE.name(), PLAYER_CREATE.name())
                                                .requestMatchers(PUT, "/api/admins/**")
                                                .hasAnyAuthority(ADMIN_UPDATE.name(), PLAYER_UPDATE.name())
                                                .requestMatchers(DELETE, "/api/admins/**")
                                                .hasAnyAuthority(ADMIN_DELETE.name(), PLAYER_DELETE.name())
                                                .anyRequest()
                                                .authenticated())
                                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                                .authenticationProvider(authenticationProvider())
                                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
                // .logout(logout -> logout.logoutUrl("/api/v1/auth/logout")
                // .addLogoutHandler(logoutHandler)
                // .logoutSuccessHandler((request, response,
                // authentication) -> SecurityContextHolder
                // .clearContext()));

                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public AuthenticationProvider authenticationProvider() {
                DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
                authProvider.setUserDetailsService(userService.userDetailsService());
                authProvider.setPasswordEncoder(passwordEncoder());
                return authProvider;
        }

        @Bean
        public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
                        throws Exception {
                return config.getAuthenticationManager();
        }
}
