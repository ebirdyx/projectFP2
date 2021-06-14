package al.edu.cit.store.models;
import lombok.Data;
import javax.persistence.*;

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
    //private Double price;

}
