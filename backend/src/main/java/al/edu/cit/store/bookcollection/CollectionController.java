package al.edu.cit.store.bookcollection;

import al.edu.cit.store.exceptions.CollectionNotFoundException;
import al.edu.cit.store.bookcollection.BookCollection;
import al.edu.cit.store.bookcollection.CollectionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(path = "/api/v1/collections")
public class CollectionController {

    private final CollectionService collectionService;

    @GetMapping
    public List<BookCollection> getCollections() {
        return collectionService.getCollection();
    }

    @GetMapping("{id}")
    public BookCollection getBookCollection(@PathVariable Long id) throws CollectionNotFoundException {
        return collectionService.getCollection(id);
    }

    @DeleteMapping("{id}")
    public void deleteBookCollection(@PathVariable Long id) throws CollectionNotFoundException {
        collectionService.deleteCollection(id);
    }

    @PostMapping
    public BookCollection createBookCollection(@RequestBody BookCollection collection) {
        return collectionService.createCollection(collection);
    }

    @PatchMapping("{id}")
    public BookCollection updateBookCollection(@PathVariable Long id, @RequestBody BookCollection collection) throws CollectionNotFoundException {
        return collectionService.updateCollection(id, collection);
    }
}
