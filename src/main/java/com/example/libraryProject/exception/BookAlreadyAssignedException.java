package com.example.libraryProject.exception;

/**
 * Исключение, которое выбрасывается, когда книга уже присвоена пользователю
 *
 * @author ITWeiss
 */
public class BookAlreadyAssignedException extends RuntimeException {
  public BookAlreadyAssignedException(String ownerName) {
    super(String.format("Book is already assigned to %s", ownerName));
  }
}
