package io.kyorg.springsecuritydemo.books;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String isbn;
    private String title;
    private String description;
    private String imageUrl;
    private String language;
    private Integer numPages;
    private String publisher;
}
