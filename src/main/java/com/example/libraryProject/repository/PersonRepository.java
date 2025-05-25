package com.example.libraryProject.repository;

import com.example.libraryProject.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * Реализация репозитория для работы с пользователями
 *
 * @author ITWeiss
 */
public interface PersonRepository extends JpaRepository<Person, Long> {
  Optional<Person> findByName(String name);
}
