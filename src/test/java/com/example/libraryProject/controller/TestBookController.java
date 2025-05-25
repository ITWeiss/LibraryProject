package com.example.libraryProject.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.libraryProject.config.PostgresDbTestcontainers;
import com.example.libraryProject.entity.Book;
import com.example.libraryProject.restController.BookController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

/**
 * Класс для тестирования контроллера для работы с книгами
 *
 * @author ITWeiss
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Тестирование контроллера для работы с книгами")
public class TestBookController extends PostgresDbTestcontainers {


  @Autowired
  private BookController bookController;

  @Test
  @DisplayName("Получение всех задач")
  void testGetAllTasks() {

    List<Book> books = bookController.getAllBooks();

    assertThat(books).isNotNull();
    assertEquals(3, books.size());
  }


}
