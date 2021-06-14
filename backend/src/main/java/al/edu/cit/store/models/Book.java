package al.edu.cit.store.models;

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

    private Visibility visibility;

    @ManyToMany
    private List<BookCollection> collections;

    @ManyToMany
    private List<Genre> categories;
}
