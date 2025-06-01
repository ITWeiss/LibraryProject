package com.example.libraryProject.config;

import com.example.libraryProject.entity.Person;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

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
   * Получение идентификатора текущего пользователя
   */
  public static Long getCurrentUserId() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof Person person) {
      return person.getId();
    }
    throw new IllegalStateException("Unable to determine current user");
  }
}

