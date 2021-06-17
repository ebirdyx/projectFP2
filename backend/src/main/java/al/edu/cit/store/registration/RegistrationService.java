package al.edu.cit.store.registration;

import al.edu.cit.store.appuser.AppUser;
import al.edu.cit.store.appuser.AppUserRole;
import al.edu.cit.store.appuser.AppUserService;
import al.edu.cit.store.exceptions.EmailAlreadyExistsException;
import al.edu.cit.store.exceptions.InvalidEmailException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {
    private final AppUserService appUserService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request)
            throws InvalidEmailException, EmailAlreadyExistsException {

        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail)
            throw new InvalidEmailException();

        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER));
    }
}
