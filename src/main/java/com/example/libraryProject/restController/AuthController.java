package com.example.libraryProject.restController;

import com.example.libraryProject.entity.Person;
import com.example.libraryProject.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
      return ResponseEntity.badRequest().build();
    }
    userService.createUser(person);
    return ResponseEntity.ok(person);
  }

  /**
   * Авторизация пользователя
   */
  @PostMapping("/login")
  public ResponseEntity<Person> login(Person person) {
    if (userService.getUser(person).isPresent()) {
      return ResponseEntity.ok(person);
    } else {
      return ResponseEntity.badRequest().build();
    }

  }
}
