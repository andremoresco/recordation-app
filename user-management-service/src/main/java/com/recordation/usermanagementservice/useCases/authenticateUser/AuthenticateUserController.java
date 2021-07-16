package com.recordation.usermanagementservice.useCases.authenticateUser;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticateUserController {

    private final AuthenticateUserUseCase authenticateUserUseCase;

    public AuthenticateUserController(AuthenticateUserUseCase authenticateUserUseCase) {
        this.authenticateUserUseCase = authenticateUserUseCase;
    }

    @PostMapping("/users/login")
    public ResponseEntity<Object> handle(@RequestParam String username, @RequestParam String password) {
        String execute = authenticateUserUseCase.execute(username, password);
        return ResponseEntity.ok(AuthenticationHttpResponse.builder().token(execute).build());
    }

}
