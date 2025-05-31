package com.example.libraryProject.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.libraryProject.config.PostgresDbTestcontainers;
import com.example.libraryProject.entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import java.util.List;

/**
 * Класс для тестирования репозитория для работы с книгами
 *
 * @author ITWeiss
 */
@DataJpaTest
@DisplayName("Тестирование репозитория для работы с пользователями")
@Import(PostgresDbTestcontainers.class)
class TestBookRepository {

  @Autowired
  private BookRepository bookRepository;

  @Test
  @DisplayName("Получение списка книг")
  void shouldGetList() {
    List<Book> users = bookRepository.findAll();

    assertThat(users).isNotEmpty();
    assertEquals(3, users.size());
  }
}