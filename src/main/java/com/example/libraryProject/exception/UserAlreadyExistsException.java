package com.example.libraryProject.exception;

/**
 * Исключение, которое выбрасывается, когда пользователь уже существует
 *
 * @author ITWeiss
 */
public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException(String message) {
    super(message);
  }
}
