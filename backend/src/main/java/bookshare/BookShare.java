package bookshare;

import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class BookShare {

    public static void main(String[] args) {
        SpringApplication.run(BookShare.class, args);
    }

}
