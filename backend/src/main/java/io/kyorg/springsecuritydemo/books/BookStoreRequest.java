package io.kyorg.springsecuritydemo.books;

import lombok.Data;

@Data
public class BookStoreRequest {
    private String name;
    private String url;
}
