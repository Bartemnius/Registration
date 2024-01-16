package com.project.security.registration;

import com.project.security.appuser.AppUser;
import com.project.security.appuser.AppUserRole;
import com.project.security.appuser.AppUserService;
import com.project.security.registration.token.ConfirmationToken;
import com.project.security.registration.token.ConfirmationTokenService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    
    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if(!isValidEmail) {
            throw new IllegalStateException("Email not valid!");
        }
        return appUserService.singUpUser(new AppUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                request.getPassword(),
                AppUserRole.USER
        ));
    }


    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token);

        if(confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException("Email already confirmed!");
        }

        LocalDateTime expiredAt = confirmationToken.getExpiresAt();

        if(expiredAt.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Token expired!");
        }

        confirmationTokenService.enableAppUser(confirmationToken.getAppUser().getEmail());

        return "Confirmed!";
    }
}
