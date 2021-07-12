package com.recordation.usermanagementservice.useCases.findUserByIdentifier;

import com.recordation.usermanagementservice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindUserByIdentifierController {

    private final FindUserByIdentifierUseCase findUserByIdentifierUseCase;

    @Autowired
    public FindUserByIdentifierController(FindUserByIdentifierUseCase findUserByIdentifierUseCase) {
        this.findUserByIdentifierUseCase = findUserByIdentifierUseCase;
    }

    @GetMapping("/users/{identifier}")
    public ResponseEntity<Object> find(@PathVariable("identifier") String identifier) {
        User user = this.findUserByIdentifierUseCase.loadUserByUsername(identifier);
        return ResponseEntity.ok(user);
    }

}
