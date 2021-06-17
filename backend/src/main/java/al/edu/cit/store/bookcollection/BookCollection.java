package al.edu.cit.store.bookcollection;
import al.edu.cit.store.book.Book;
import al.edu.cit.store.common.Visibility;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "bookCollection")
public class BookCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private Visibility visibility;

    @OneToOne
    private BookCollection parent;

    @ManyToMany
    private List<Book> books = new ArrayList<>();
}
