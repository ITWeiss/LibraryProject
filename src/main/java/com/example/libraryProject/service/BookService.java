package com.example.libraryProject.service;

import com.example.libraryProject.config.UserUtils;
import com.example.libraryProject.entity.Book;
import com.example.libraryProject.entity.Person;
import com.example.libraryProject.repository.BookRepository;
import com.example.libraryProject.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    String currentUser = UserUtils.getCurrentUsername();

    book.setCreatedPerson(currentUser);
    book.setCreatedAt(UserUtils.getCurrentTime());
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
    book.setRemovedAt(UserUtils.getCurrentTime());
    book.setRemovedPerson(UserUtils.getCurrentUsername());
    repository.deleteById(id);
  }

  /**
   * Получить книгу по ID
   */
  @Transactional
  public Optional<Book> getBookById(Long id) {
    return Optional.ofNullable(repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found")));
  }

  /**
   * Назначить книгу текущему пользователю
   */
  @Transactional
  public void assignBookToCurrentUser(Long id) {
    String username = UserUtils.getCurrentUsername();
    Book book = repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Book with id " + id + " not found"));

    Person owner = personRepository.findByName(username)
        .orElseThrow(() -> new RuntimeException("Person with name " + username + " not found"));

    if (book.getOwner() != null) {
      throw new RuntimeException("Book is already assigned to" + book.getOwner().getName());
    }

    book.setOwner(owner);
    repository.save(book);
  }

  /**
   * Обновить книгу
   */
  @Transactional
  public void updateBook(Long id, Book book) {
    if (repository.existsById(id)) {
      book.setUpdatedAt(UserUtils.getCurrentTime());
      book.setUpdatedPerson(UserUtils.getCurrentUsername());
      book.setId(id);
      repository.save(book);
    } else {
      throw new RuntimeException("Book with id " + id + " not found");
    }
  }

  /**
   * Получить обложку книги
   */
  @Transactional
  public String getBookCover(Long id) {
    if (repository.existsById(id)) {
      Book book = repository.findById(id).get();
      return String.format("Book: %s %s. Year of publication: %d",
          book.getAuthor(), book.getName(), book.getYearOfPublication());
    } else {
      throw new RuntimeException("Book with id " + id + " not found");
    }
  }
}
