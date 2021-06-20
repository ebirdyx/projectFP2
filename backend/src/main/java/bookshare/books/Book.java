package bookshare.books;

import lombok.Data;
import org.hibernate.annotations.Type;

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

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    private String imageUrl;
    private String language;
    private Integer numPages;
    private String publisher;

    private Integer yearOfPublication;

    private String fileName;
}
