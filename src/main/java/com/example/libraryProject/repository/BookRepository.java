package com.example.libraryProject.repository;

import com.example.libraryProject.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

/**
 * Реализация репозитория для работы с книгами
 *
 * @author ITWeiss
 */
public interface BookRepository extends JpaRepository<Book, Long> {
  Optional<Book> findByName(String name);

  List<Book> findByOwnerId(Long ownerId);
}
