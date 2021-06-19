package io.kyorg.springsecuritydemo.appuser;

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
        AppUser user = appUserRepository.findByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, username));

        return user;
    }

    public AppUser createUser(AppUser user) {
        Authority role = getUserAuthority();

        user.setAuthorities(Collections.singletonList(role));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        appUserRepository.save(user);

        return appUserRepository.findByUsername(user.getUsername());
    }

    public Authority getUserAuthority() {
        Optional<Authority> role = authorityRepository.findByAppUserRole(AppUserRole.USER);
        return role.orElseGet(() -> Authority.createAuthority(AppUserRole.USER, "User role"));
    }

    @PostConstruct
    protected void initUsers() {
        Authority adminAuthority = Authority.createAuthority(AppUserRole.ADMIN, "Admin role");

        AppUser admin = createUser(
                "admin",
                "admin",
                "admin",
                "admin",
                "admin@local",
                adminAuthority);

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
