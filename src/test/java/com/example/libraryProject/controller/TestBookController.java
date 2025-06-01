package com.example.libraryProject.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.example.libraryProject.config.PostgresDbTestcontainers;
import com.example.libraryProject.entity.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Класс для тестирования контроллера для работы с книгами
 *
 * @author ITWeiss
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("Тестирование контроллера для работы с книгами")
public class TestBookController extends PostgresDbTestcontainers {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  @DisplayName("Получение всех книг")
  void testGetAllBooks() {

    ResponseEntity<Book[]> books = restTemplate.getForEntity(
        "http://localhost:8080/books",
        Book[].class
    );
    assertEquals(HttpStatus.OK, books.getStatusCode());
    Book[] booksArray = books.getBody();
    assertThat(booksArray).isNotNull();
    assertEquals(3, booksArray.length);
  }

}
