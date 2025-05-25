package com.example.libraryProject.service;

import com.example.libraryProject.config.MyUserDetails;
import com.example.libraryProject.entity.Person;
import com.example.libraryProject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Optional;

/**
 * Класс для загрузки пользователей
 *
 * @author ITWeiss
 */
public class MyUserDetailsService implements UserDetailsService {

  @Autowired
  private PersonRepository repository;

  /**
   * Загрузка пользователя по имени
   */
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<Person> person = repository.findByName(username);

    return person.map(MyUserDetails::new)
        .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

  }
}
