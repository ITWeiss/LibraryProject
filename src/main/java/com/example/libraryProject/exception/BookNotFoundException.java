package com.example.libraryProject.exception;

/**
 * Исключение, которое выбрасывается, когда книга не найдена
 *
 * @author ITWeiss
 */
public class BookNotFoundException extends RuntimeException {
  public BookNotFoundException(Long id) {
    super("Книга с ID " + id + " не найдена");
  }
}
