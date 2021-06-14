package al.edu.cit.store.models;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "collections")
public class BookCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;
    private String description;

    private Visibility visibility;

    @OneToOne
    private BookCollection parent;

    @ManyToMany
    private List<Book> books = new ArrayList<>();
}
