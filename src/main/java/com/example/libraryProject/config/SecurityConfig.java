package com.example.libraryProject.config;

import com.example.libraryProject.service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;

/**
 * Класс конфигурации безопасности
 *
 * @author ITWeiss
 */
@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

  /**
   * Создание сервиса пользователей
   */
  @Bean
  public UserDetailsService userDetailsService() {
    return new MyUserDetailsService();
  }

  /**
   * Настройка безопасности
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/books/**").hasAnyRole("USER", "ADMIN")
            .anyRequest().authenticated()
        )
        .logout(logout -> logout.logoutUrl("/logout")
            .logoutSuccessUrl("/login")
            .permitAll())
        .oauth2ResourceServer((oath2) -> oath2.jwt(Customizer.withDefaults()));

    return http.build();
  }

  /**
   * Настройка кодировщика паролей
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance();
  }

  /**
   * Создание менеджера аутентификации
   */
  @Bean
  public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder);
    return new ProviderManager(authProvider);
  }

}
