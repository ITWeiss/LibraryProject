package com.example.libraryProject.service;

import com.example.libraryProject.config.UserUtils;
import com.example.libraryProject.entity.Book;
import com.example.libraryProject.entity.Person;
import com.example.libraryProject.exception.BookAlreadyAssignedException;
import com.example.libraryProject.exception.BookNotFoundException;
import com.example.libraryProject.exception.UserNotFoundException;
import com.example.libraryProject.repository.BookRepository;
import com.example.libraryProject.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Сервис для работы с книгами
 *
 * @author ITWeiss
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

  private final BookRepository repository;
  private final PersonRepository personRepository;

  /**
   * Добавить книгу
   */
  @Transactional
  public void addBook(Book book) {
    Optional<Book> existingBook = repository.findByName(book.getName());
    if (existingBook.isPresent()) {
      throw new RuntimeException("Book with name '" + book.getName() + "' already exists");
    }
    Long currentUserId = UserUtils.getCurrentUserId();

    book.setCreatedPerson(currentUserId.toString());
    book.setCreatedAt(LocalDateTime.now());
    repository.save(book);
  }

  /**
   * Получить все книги
   */
  @Transactional
  public List<Book> getAllBooks() {
    return repository.findAll();
  }

  /**
   * Получить книги по владельцу
   */
  @Transactional
  public List<Book> getBooksByOwner(Long userid) {
    return repository.findByOwnerId(userid);
  }

  /**
   * Удалить книгу по ID
   */
  @Transactional
  public void deleteBookById(Long id) {
    Book book = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));
    book.setRemovedAt(LocalDateTime.now());
    book.setRemovedPerson(UserUtils.getCurrentUsername());
    repository.deleteById(id);
  }

  /**
   * Получить книгу по ID
   */
  @Transactional
  public Book getBookById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new BookNotFoundException(id));
  }

  /**
   * Назначить книгу текущему пользователю
   */
  @Transactional
  public void assignBookToCurrentUser(Long id) {
    Book book = repository.findById(id)
        .orElseThrow(() -> new BookNotFoundException(id));

    if (book.getOwner() != null) {
      throw new BookAlreadyAssignedException(book.getOwner().getName());
    }
    Long currentUserId = UserUtils.getCurrentUserId();
    Person owner = personRepository.findById(currentUserId)
        .orElseThrow(() -> new UserNotFoundException("User not found"));
    book.setOwner(owner);
    repository.save(book);
  }

  /**
   * Получить обложку книги
   */
  @Transactional
  public String getBookCover(Long id) {
    Book book = getBookById(id);
    return String.format("Book: %s %s. Year of publication: %d",
        book.getAuthor(), book.getName(), book.getYearOfPublication());
  }

  /**
   * Обновить книгу
   */
  @Transactional
  public void updateBook(Long id, Book book) {
    if (!repository.existsById(id)) {
      throw new BookNotFoundException(id);
    }
    book.setUpdatedAt(LocalDateTime.now());
    book.setUpdatedPerson(UserUtils.getCurrentUsername());
    book.setId(id);
    repository.save(book);
  }
}
