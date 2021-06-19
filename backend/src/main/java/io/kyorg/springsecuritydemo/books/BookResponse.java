package io.kyorg.springsecuritydemo.books;

import lombok.Data;

@Data
public class BookResponse {
    private Long id;
    private String isbn;
    private String title;
    private String description;
    private String imageUrl;
    private String language;
    private Integer numPages;
    private String publisher;
}
