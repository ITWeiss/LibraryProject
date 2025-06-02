package com.example.libraryProject.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

/**
 * Сущность книги
 *
 * @author ITWeiss
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String author;
  private String annotation;
  private Integer yearOfPublication;

  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private LocalDateTime removedAt;

  @ManyToOne
  private Person createdPerson;
  private String updatedPerson;
  private String removedPerson;

  @ManyToOne
  @JoinColumn(name = "owner_id")
  private Person owner;
}
