package com.example.libraryProject.service;

import com.example.libraryProject.entity.Person;
import com.example.libraryProject.exception.UserAlreadyExistsException;
import com.example.libraryProject.exception.UserNotFoundException;
import com.example.libraryProject.repository.BookRepository;
import com.example.libraryProject.repository.PersonRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    if (personRepository.findByName(person.getName()).isPresent()) {
      throw new UserAlreadyExistsException("User with the same details already exists");
    }
    String encodedPassword = passwordEncoder.encode(person.getPassword());
    person.setPassword(encodedPassword);
    personRepository.save(person);
  }

  /**
   * Получить пользователя по имени
   */
  @Transactional
  public Person getUser(Person person) {
    return personRepository.findByName(person.getName())
        .orElseThrow(() -> new UserNotFoundException("User not found"));
  }
}
