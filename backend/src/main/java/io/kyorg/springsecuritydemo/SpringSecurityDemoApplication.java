package io.kyorg.springsecuritydemo;

import io.kyorg.springsecuritydemo.appuser.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class SpringSecurityDemoApplication {

    private final PasswordEncoder passwordEncoder;
    private final AppUserRepository appUserRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemoApplication.class, args);
    }

    @PostConstruct
    protected void initUsers() {
        Authority adminAuthority = createAuthority(AppUserRole.ADMIN, "User role");

        AppUser admin = createUser(
                "admin",
                "admin",
                "admin",
                "admin",
                "admin@local",
                adminAuthority);

        appUserRepository.save(admin);
    }

    private AppUser createUser(
            String username,
            String password,
            String firstName,
            String lastName,
            String email,
            Authority authority
    ) {
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAuthorities(Collections.singletonList(authority));
        return user;
    }

    private Authority createAuthority(AppUserRole role, String description) {
        Authority authority = new Authority();
        authority.setAppUserRole(role);
        authority.setRoleDescription(description);
        return authority;
    }
}
