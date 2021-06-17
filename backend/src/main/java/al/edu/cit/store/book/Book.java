package al.edu.cit.store.book;

import al.edu.cit.store.bookcollection.BookCollection;
import al.edu.cit.store.genre.Genre;
import al.edu.cit.store.common.Visibility;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String isbn;
    private String title;
    private String description;
    private String author;
    private Integer pages;
    private String publisher;
    private String language;

    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    @ManyToMany
    private List<BookCollection> collections;

    @ManyToMany
    private List<Genre> categories;
}
