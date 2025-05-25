package com.example.libraryProject.config;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import java.time.LocalDateTime;

/**
 * Класс со статическими методами для работы с данными пользователя
 *
 * @author ITWeiss
 */
public class UserUtils {

  /**
   * Получение имени текущего пользователя
   */
  public static String getCurrentUsername() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails userDetails) {
      return userDetails.getUsername();
    }
    return principal.toString();
  }

  /**
   * Получение текущего времени
   */
  public static LocalDateTime getCurrentTime() {
    return LocalDateTime.now();
  }
}

