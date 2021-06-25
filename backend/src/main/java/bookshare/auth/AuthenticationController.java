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

        // Create an authentication from the request
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Set authentication context to this user
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Get the user
        AppUser user = (AppUser) authentication.getPrincipal();

        // Generate a token for this user
        String jwtToken = jwtTokenHelper.generateToken(user.getUsername());

        // Create response with a token to return to the client
        AuthenticationResponse response = new AuthenticationResponse();
        response.setToken(jwtToken);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/userinfo")
    public ResponseEntity<?> getUserInfo(Principal principal) {

        // ask appUserService for the user infornmation
        AppUser user = (AppUser) appUserService.loadUserByUsername(principal.getName());

        // map user to an AppUserResponse instance
        AppUserResponse response = mapper.map(user, AppUserResponse.class);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest request)
            throws IncorrectEmailFormatException {

        // check the email format
        if (!request.getEmail().matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$"))
            throw new IncorrectEmailFormatException();

        // mapping the request attributes to an AppUser instance
        AppUser requestUser = mapper.map(request, AppUser.class);

        // calling AppUserService to create the user with our requestUser
        AppUser createdUser = appUserService.createUser(requestUser);

        // mapping the createdUser attributes to an AppUserResponse instance
        AppUserResponse response = mapper.map(createdUser, AppUserResponse.class);

        return ResponseEntity.ok(response);
    }
}
