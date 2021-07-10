package com.recordation.usermanagementservice.useCases.createUser;

import com.recordation.usermanagementservice.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateUserController {

    private final CreateUserUseCase createUserUseCase;

    @Autowired
    public CreateUserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> handle(@RequestBody CreateUserDTO createUserDTO) {
        try {

            ModelMapper modelMapper = new ModelMapper();
            User user = modelMapper.map(createUserDTO, User.class);

            this.createUserUseCase.execute(user);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
