package com.example.libraryProject.config;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

/**
 * Класс для настройки контейнера базы данных PostgreSQL для тестирования
 *
 * @author ITWeiss
 */
public class PostgresDbTestcontainers {
  @Container
  @ServiceConnection
  static final PostgreSQLContainer<?> postgresContainer =
      new PostgreSQLContainer<>("postgres:15-alpine");

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgresContainer::getUsername);
    registry.add("spring.datasource.password", postgresContainer::getPassword);

    registry.add("spring.flyway.url", postgresContainer::getJdbcUrl);
    registry.add("spring.flyway.user", postgresContainer::getUsername);
    registry.add("spring.flyway.password", postgresContainer::getPassword);
  }
}
