package al.edu.cit.store.controllers;

import al.edu.cit.store.exceptions.CollectionNotFoundException;
import al.edu.cit.store.models.BookCollection;
import al.edu.cit.store.services.CollectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CollectionController {
    private CollectionService collectionService;

    public CollectionController(CollectionService collectionService) {
        this.collectionService = collectionService;
    }

    @GetMapping("/collections")
    public List<BookCollection> getCollections() {
        return collectionService.getCollection();
    }

    @GetMapping("/collections/{id}")
    public BookCollection getBookCollection(@PathVariable Long id) throws CollectionNotFoundException {
        return collectionService.getCollection(id);
    }

    @DeleteMapping("/collections/{id}")
    public void deleteBookCollection(@PathVariable Long id) throws CollectionNotFoundException {
        collectionService.deleteCollection(id);
    }

    @PostMapping("/collections")
    public BookCollection createBookCollection(@RequestBody BookCollection collection) {
        return collectionService.createCollection(collection);
    }

    @PatchMapping("/collections/{id}")
    public BookCollection updateBookCollection(@PathVariable Long id, @RequestBody BookCollection collection) throws CollectionNotFoundException {
        return collectionService.updateCollection(id, collection);
    }
}
