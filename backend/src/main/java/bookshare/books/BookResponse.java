package bookshare.books;

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
    private Integer yearOfPublication;
    private String fileName;
}
