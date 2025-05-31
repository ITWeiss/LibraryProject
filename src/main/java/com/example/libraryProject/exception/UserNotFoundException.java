package com.example.libraryProject.exception;

/**
 * Исключение, которое выбрасывается, когда пользователь не найден
 *
 * @author ITWeiss
 */
public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String username) {
    super("User with name " + username + " not found");
  }
}
