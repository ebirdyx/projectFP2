package al.edu.cit.store.services;

import al.edu.cit.store.exceptions.CollectionNotFoundException;
import al.edu.cit.store.models.BookCollection;
import al.edu.cit.store.repositories.CollectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CollectionService {
    private CollectionRepository collectionRepository;

    public CollectionService(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
    }

    public List<BookCollection> getCollection() {
        return collectionRepository.findAll();
    }

    public void deleteCollection(Long id) throws CollectionNotFoundException {
        Optional<BookCollection> collection = collectionRepository.findById(id);

        if (collection.isPresent()) {
            collectionRepository.deleteById(id);
        } else {
            throw new CollectionNotFoundException();
        }
    }

    public BookCollection createCollection(BookCollection collection) {
        return collectionRepository.save(collection);
    }

    public BookCollection getCollection(Long id) throws CollectionNotFoundException {
        Optional<BookCollection> collection = collectionRepository.findById(id);

        if (collection.isPresent()) {
            return collection.get();
        } else {
            throw new CollectionNotFoundException();
        }
    }

    public BookCollection updateCollection(Long id, BookCollection collection) throws CollectionNotFoundException {
        Optional<BookCollection> oldCollection = collectionRepository.findById(id);
        if (oldCollection.isPresent()) {

            BookCollection old = oldCollection.get();
            old.setName(collection.getName());
            old.setDescription(collection.getDescription());


            return collectionRepository.save(old);
        } else {
            throw new CollectionNotFoundException();
        }
    }
}
