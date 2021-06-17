package al.edu.cit.store.genre;

import al.edu.cit.store.genre.GenreResponse;
import al.edu.cit.store.genre.Genre;
import al.edu.cit.store.genre.GenreService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@AllArgsConstructor
@RequestMapping(path = "/api/v1/genres")
public class GenreController {

    private final GenreService genreService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<GenreResponse> getGenres() {
        List<Genre> genres = genreService.getGenres();

        return genres
                .stream()
                // modelMapper maps objects from source genre
                // to new objects instantiated from class GenreDto
                .map(genre -> modelMapper.map(genre, GenreResponse.class))
                .collect(Collectors.toList());
    }
}
