package al.edu.cit.store;

import al.edu.cit.store.appuser.AppUser;
import al.edu.cit.store.appuser.AppUserRepository;
import al.edu.cit.store.appuser.AppUserRole;
import al.edu.cit.store.genre.Genre;
import al.edu.cit.store.genre.GenreRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@AllArgsConstructor
public class StoreApplication {

	private GenreRepository genreRepository;
	private AppUserRepository appUserRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@PostConstruct
	public void initGenres() {
		List<Genre> genres = Stream.of(
				new Genre(1L, "Fantasy", null, null),
				new Genre(2L, "Adventure", null, null),
				new Genre(3L, "Romance", null, null),
				new Genre(4L, "Contemporary", null, null),
				new Genre(5L, "Dystopian", null, null),
				new Genre(6L, "Mystery", null, null),
				new Genre(7L, "Horror", null, null),
				new Genre(8L, "Thriller", null, null),
				new Genre(9L, "Paranormal", null, null),
				new Genre(10L, "Historical fiction", null, null),
				new Genre(11L, "Science Fiction", null, null),
				new Genre(12L, "Memoir", null, null),
				new Genre(13L, "Cooking", null, null),
				new Genre(14L, "Art", null, null),
				new Genre(15L, "Personal", null, null),
				new Genre(16L, "Development", null, null),
				new Genre(17L, "Motivational", null, null),
				new Genre(18L, "Health", null, null),
				new Genre(19L, "History", null, null),
				new Genre(20L, "Travel", null, null),
				new Genre(20L, "Guides", null, null),
				new Genre(21L, "Families & Relationships", null, null),
				new Genre(22L, "Humor", null, null),
				new Genre(23L, "Children", null, null)
		).collect(Collectors.toList());

		genreRepository.saveAll(genres);
	}

	@PostConstruct
	public void initUsers() {
		List<AppUser> users = Stream.of(
				new AppUser(
						"admin",
						"admin",
						"admin@local",
						bCryptPasswordEncoder.encode("asdfasdf"),
						AppUserRole.ADMIN)
		).collect(Collectors.toList());

		appUserRepository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}

}
