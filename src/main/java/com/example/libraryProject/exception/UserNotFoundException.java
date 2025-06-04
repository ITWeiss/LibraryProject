package com.example.libraryProject.exception;

/**
 * Исключение, которое выбрасывается, когда пользователь не найден
 *
 * @author ITWeiss
 */
public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException(String username) {
    super(String.format("User with name %s not found", username));
  }
}
