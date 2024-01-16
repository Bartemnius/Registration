package com.project.security.registration.token;

import com.project.security.appuser.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final AppUserRepository appUserRepository;

    public void saveConfirmationToken(ConfirmationToken confirmationToken) {
        confirmationTokenRepository.save(confirmationToken);
    }

    public ConfirmationToken getToken(String token) {
        Optional<ConfirmationToken> confirmationToken = confirmationTokenRepository.findByToken(token);
        if(confirmationToken.isPresent()) {
            return confirmationToken.get();
        }
        else throw new IllegalStateException("Token not found");
    }

    public void enableAppUser(String email) {
        appUserRepository.findByEmail(email).get().setEnabled(true);
    }
}
