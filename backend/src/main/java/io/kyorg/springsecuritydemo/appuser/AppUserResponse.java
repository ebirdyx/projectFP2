package io.kyorg.springsecuritydemo.appuser;

import lombok.Data;

@Data
public class AppUserResponse {
    private String username;
    private String firstName;
    private String lastName;
    private Object roles;
    private String email;
}
