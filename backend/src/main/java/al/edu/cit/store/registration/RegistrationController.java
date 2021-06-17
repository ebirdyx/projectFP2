package al.edu.cit.store.registration;

import al.edu.cit.store.appuser.AppUserService;
import al.edu.cit.store.exceptions.EmailAlreadyExistsException;
import al.edu.cit.store.exceptions.InvalidEmailException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/registration")
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request)
            throws InvalidEmailException, EmailAlreadyExistsException {

        return registrationService.register(request);
    }
}
