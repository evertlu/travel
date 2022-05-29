package uz.ama.travel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.ama.travel.model.AuthenticationToken;
import uz.ama.travel.repository.TokenRepository;

@Service
public class AuthenticationService {

    @Autowired
    TokenRepository tokenRepository;


    public void saveConfirmationToken(AuthenticationToken authenticationToken) {

    }
}
