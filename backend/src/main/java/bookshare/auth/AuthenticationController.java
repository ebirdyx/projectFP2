package bookshare.auth;

import bookshare.appuser.AppUser;
import bookshare.appuser.AppUserResponse;
import bookshare.appuser.AppUserService;
import bookshare.configuration.JwtTokenHelper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenHelper jwtTokenHelper;
    private final AppUserService appUserService;
    private final ModelMapper mapper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request)
            throws InvalidKeySpecException, NoSuchAlgorithmException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        AppUser user = (AppUser) authentication.getPrincipal();
        String jwtToken = jwtTokenHelper.generateToken(user.getUsername());

        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/userinfo")
    public ResponseEntity<?> getUserInfo(Principal principal) {
        AppUser user = (AppUser) appUserService.loadUserByUsername(principal.getName());

//        AppUserResponse response = new AppUserResponse();
//        response.setFirstName(user.getFirstName());
//        response.setLastName(user.getLastName());
//        response.setUsername(user.getUsername());
//        response.setRoles(user.getAuthorities().toArray());
//        response.setEmail(user.getEmail());

        return ResponseEntity.ok(mapper.map(user, AppUserResponse.class));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest request)
            throws IncorrectEmailFormatException {

        if (!request.getEmail().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"))
            throw new IncorrectEmailFormatException();

//        AppUser user = new AppUser();
//        user.setUsername(request.getUsername());
//        user.setFirstName(request.getFirstName());
//        user.setLastName(request.getLastName());
//        user.setEmail(request.getEmail());
//        user.setPassword(request.getPassword());

        AppUser createdUser = appUserService.createUser(mapper.map(request, AppUser.class));

//        AppUserResponse response = new AppUserResponse();
//        response.setFirstName(createdUser.getFirstName());
//        response.setLastName(createdUser.getLastName());
//        response.setUsername(createdUser.getUsername());
//        response.setRoles(createdUser.getAuthorities().toArray());

        return ResponseEntity.ok(mapper.map(createdUser, AppUserResponse.class));
    }
}
