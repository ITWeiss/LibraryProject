package com.example.libraryProject.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Сущность пользователя
 *
 * @author ITWeiss
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "persons")
public class Person {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Integer age;
  private String email;
  private String phoneNumber;
  private String password;
  private String role;
  private LocalDateTime createdAt;
  private LocalDateTime removedAt;

  @ManyToOne
  private Person createdPerson;
  private String removedPerson;

  @OneToMany(mappedBy = "owner")
  private List<Book> books;
}
