package al.edu.cit.store.controllers;

import al.edu.cit.store.dtos.GenreDto;
import al.edu.cit.store.models.Genre;
import al.edu.cit.store.services.GenreService;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class GenreController {
    GenreService genreService;
    ModelMapper modelMapper;

    public GenreController(GenreService genreService) {
        this.genreService = genreService;
        modelMapper = new ModelMapper();
    }

    @GetMapping("/genres")
    public List<GenreDto> getGenres() {
        List<Genre> genres = genreService.getGenres();

        return genres
                .stream()
                // modelMapper maps objects from source genre
                // to new objects instantiated from class GenreDto
                .map(genre -> modelMapper.map(genre, GenreDto.class))
                .collect(Collectors.toList());
    }
}
