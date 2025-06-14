package com.example.libraryProject.config;

import com.example.libraryProject.entity.Person;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Класс для создания объекта пользователя, который будет использоваться для аутентификации и авторизации.
 *
 * @author ITWeiss
 */
@AllArgsConstructor
public class MyUserDetails implements UserDetails {

  private final Person person;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return Arrays.stream(person.getRole().split(", "))
        .map(SimpleGrantedAuthority::new)
        .collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return person.getPassword();
  }

  @Override
  public String getUsername() {
    return person.getName();
  }

  @Override
  public boolean isAccountNonExpired() {
    return UserDetails.super.isAccountNonExpired();
  }

  @Override
  public boolean isAccountNonLocked() {
    return UserDetails.super.isAccountNonLocked();
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return UserDetails.super.isCredentialsNonExpired();
  }

  @Override
  public boolean isEnabled() {
    return UserDetails.super.isEnabled();
  }


}
