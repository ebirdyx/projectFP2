package bookshare.books;

import lombok.Data;

@Data
public class BookRequest {
    private String isbn;
    private String title;
    private String description;
    private String imageUrl;
    private String language;
    private Integer numPages;
    private String publisher;
    private Integer yearOfPublication;
}
