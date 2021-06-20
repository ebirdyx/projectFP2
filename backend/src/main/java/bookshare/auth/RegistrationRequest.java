package bookshare.auth;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
