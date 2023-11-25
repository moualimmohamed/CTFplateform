package com.ctf.v1.config;
//import com.alibou.security.auditing.ApplicationAuditAware;

import com.ctf.v1.repository.PlayerRepository;
//import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.domain.AuditorAware;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final PlayerRepository repository;

    @Bean
    public UserDetailsService userDetailsService() {
        // return username -> repository.findByEmail(username)
        // .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) {
                return repository.findByEmail(username)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }

    // @Bean
    // public AuthenticationProvider authenticationProvider() {
    // DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    // authProvider.setUserDetailsService(userDetailsService());
    // authProvider.setPasswordEncoder(passwordEncoder());
    // return authProvider;
    // }

    // @Bean
    // public AuditorAware<Integer> auditorAware() {
    // return new ApplicationAuditAware();
    // }

    // @Bean
    // public AuthenticationManager
    // authenticationManager(AuthenticationConfiguration config) throws Exception {
    // return config.getAuthenticationManager();
    // }

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    // return new BCryptPasswordEncoder();
    // }

}
