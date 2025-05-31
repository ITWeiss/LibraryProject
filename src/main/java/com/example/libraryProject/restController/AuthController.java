package com.example.libraryProject.restController;

import com.example.libraryProject.entity.Person;
import com.example.libraryProject.exception.UserAlreadyExistsException;
import com.example.libraryProject.exception.UserNotFoundException;
import com.example.libraryProject.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

/**
 * Контроллер для регистрации и авторизации пользователя
 *
 * @author ITWeiss
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class AuthController {

  private final PersonService userService;

  /**
   * Регистрация пользователя
   */
  @PostMapping("/register")
  public ResponseEntity<Person> register(Person person) {
    if (userService.getUser(person).isPresent()) {
      throw new UserAlreadyExistsException("User with the same details already exists");
    }
    userService.createUser(person);
    return ResponseEntity.ok(person);
  }

  /**
   * Авторизация пользователя
   */
  @PostMapping("/login")
  public ResponseEntity<Person> login(Person person) {
    Optional<Person> dbPerson = userService.getUser(person);
    if (dbPerson.isEmpty()) {
      throw new UserNotFoundException("User not found");
    }
    return ResponseEntity.ok(dbPerson.get());
  }

}

