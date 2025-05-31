package com.example.libraryProject.service;

import com.example.libraryProject.entity.Person;
import com.example.libraryProject.repository.BookRepository;
import com.example.libraryProject.repository.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Сервис для работы с пользователями
 *
 * @author ITWeiss
 */
@Service
@RequiredArgsConstructor
public class PersonService {

  private final PersonRepository personRepository;
  private final BookRepository bookRepository;
  private final PasswordEncoder passwordEncoder;


  /**
   * Создать пользователя
   */
  @Transactional
  public void createUser(Person person) {
    if (personRepository.findByName(person.getName()).isEmpty()) {
      person.setPassword(passwordEncoder.encode(person.getPassword()));
      personRepository.save(person);
    }
  }

  /**
   * Получить пользователя по имени
   */
  @Transactional
  public Optional<Person> getUser(Person person) {
    if (bookRepository.findByName(person.getName()).isPresent()) {
      return personRepository.findByName(person.getName());
    } else {
      return Optional.empty();
    }
  }
}
