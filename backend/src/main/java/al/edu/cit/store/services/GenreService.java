package al.edu.cit.store.services;

import al.edu.cit.store.dtos.GenreDto;
import al.edu.cit.store.models.Genre;
import al.edu.cit.store.repositories.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreService {
    GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getGenres() {
        return this.genreRepository.findAll();
    }
}
