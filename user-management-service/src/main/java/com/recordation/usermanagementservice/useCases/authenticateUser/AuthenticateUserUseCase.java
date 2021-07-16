package com.recordation.usermanagementservice.useCases.authenticateUser;

import com.recordation.usermanagementservice.security.JwtTokenProvider;
import com.recordation.usermanagementservice.useCases.findUserByIdentifier.FindUserByIdentifierUseCase;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateUserUseCase {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final FindUserByIdentifierUseCase findUserByIdentifierUseCase;

    public AuthenticateUserUseCase(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, FindUserByIdentifierUseCase findUserByIdentifierUseCase) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.findUserByIdentifierUseCase = findUserByIdentifierUseCase;
    }

    public String execute(String username, String password) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        return this.jwtTokenProvider.createToken(username, findUserByIdentifierUseCase.loadUserByUsername(username).getRole().name());
    }
}
