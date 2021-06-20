package io.kyorg.springsecuritydemo.books;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookStoreResponse {
    private String content;
}
