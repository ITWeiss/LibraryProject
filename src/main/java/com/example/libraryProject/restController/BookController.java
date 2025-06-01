package com.example.libraryProject.restController;

import com.example.libraryProject.entity.Book;
import com.example.libraryProject.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.util.List;

/**
 * Контроллер для работы с книгами
 *
 * @author ITWeiss
 */
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Slf4j
public class BookController {

  private final BookService bookService;

  /**
   * Получение списка всех книг
   */
  @GetMapping
  public ResponseEntity<List<Book>> getAllBooks() {
    return ResponseEntity.ok(bookService.getAllBooks());
  }

  /**
   * Получение книги по её id
   */
  @GetMapping("/{id}")
  public ResponseEntity<Book> getBookById(@PathVariable Long id) {
    return ResponseEntity.ok(bookService.getBookById(id));
  }

  /**
   * Получение обложки книги по её id
   */
  @GetMapping("/coverBook/{id}")
  public ResponseEntity<String> getBookCoverById(@PathVariable Long id) {
    return ResponseEntity.ok(bookService.getBookCover(id));
  }

  /**
   * Метод для назначения книги пользователю
   */
  @PostMapping("/assign/{id}")
  public ResponseEntity<Void> assignBookToCurrentUser(@PathVariable("id") Long bookId) {
    log.info("Received request to assign book with id {}", bookId);
    bookService.assignBookToCurrentUser(bookId);
    return ResponseEntity.ok().build();

  }

  /**
   * Метод для создания книги
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PostMapping
  public ResponseEntity<Book> createBook(@RequestBody Book book) {
    bookService.addBook(book);
    URI location = URI.create("/books/" + book.getId());
    return ResponseEntity
        .created(location)
        .build();
  }

  /**
   * Метод для удаления книги по её id
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
    bookService.deleteBookById(id);
    return ResponseEntity.noContent().build();
  }

  /**
   * Метод для обновления книги
   */
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @PutMapping("/{id}")
  public ResponseEntity<Book> updateBookById(@PathVariable Long id, @RequestBody Book book) {
    bookService.updateBook(id, book);
    return ResponseEntity.ok().build();
  }

  /**
   * Метод для получения книг по id владельца
   */
  @GetMapping("/user/{userId}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<Book>> getBooksByOwner(@PathVariable Long ownerId) {
    log.info("Received request to get books by ownerId {}", ownerId);
    return ResponseEntity.ok(bookService.getBooksByOwner(ownerId));
  }

}
