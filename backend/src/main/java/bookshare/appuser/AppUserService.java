package bookshare.appuser;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final String USER_NOT_FOUND_MSG = "username %s not found!";

    private final AppUserRepository appUserRepository;
    private final AuthorityRepository authorityRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = appUserRepository.findByUsername(username);

        if (user.isEmpty())
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username));

        return user.get();
    }

    public AppUser createUser(AppUser user) {
        // Get non-admin role authority
        Authority role = getUserAuthority();

        // Assign the non-admin role authority to the new user
        user.setAuthorities(Collections.singletonList(role));

        // Transform the password of the user to an encrypted format
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        // Save the new user
        appUserRepository.save(user);

        // return the newly created user from the database
        AppUser createdUser = appUserRepository.findByUsername(user.getUsername()).get();

        return createdUser;
    }

    public Authority getUserAuthority() {
        // Search for the normal USER authority in the database
        Optional<Authority> role = authorityRepository.findByAppUserRole(AppUserRole.USER);

        // return the user authority or create it if it doesn't exist
        return role
                .orElseGet(() ->
                        Authority.createAuthority(AppUserRole.USER, "User role"));
    }

    // Spring runs this method on first start
    @PostConstruct
    protected void initUsers() {
        // Search for the admin user
        Optional<AppUser> adminExist = appUserRepository.findByUsername("admin");

        // if the admin already exist we return
        if (adminExist.isPresent())
            return;

        // Create the admin role authority
        Authority adminAuthority = Authority.createAuthority(AppUserRole.ADMIN, "Admin role");

        // Create an AppUser admin
        AppUser admin = createUser(
                "admin",
                "admin",
                "admin",
                "admin",
                "admin@local",
                adminAuthority);

        // Save the admin user to the database
        appUserRepository.save(admin);
    }

    public AppUser createUser(
            String username,
            String password,
            String firstName,
            String lastName,
            String email,
            Authority authority
    ) {
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setAuthorities(Collections.singletonList(authority));
        return user;
    }
}
