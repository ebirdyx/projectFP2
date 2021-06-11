package al.edu.cit.store.models;

import lombok.Data;

import javax.persistence.*;

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
    private String language;
    private Integer numberOfPages;
    private Integer publisher;
}
