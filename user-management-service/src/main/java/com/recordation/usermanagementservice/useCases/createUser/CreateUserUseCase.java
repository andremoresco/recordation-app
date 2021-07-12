package com.recordation.usermanagementservice.useCases.createUser;

import com.recordation.usermanagementservice.exceptions.UserAlreadyRegisteredException;
import com.recordation.usermanagementservice.exceptions.UserArgumentsNotValidException;
import com.recordation.usermanagementservice.model.User;
import com.recordation.usermanagementservice.model.UserRole;
import com.recordation.usermanagementservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CreateUserUseCase(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void execute(User user) throws Exception {
        Optional<String> notValid = user.isNotValid();
        if (notValid.isPresent()) {
            throw new UserArgumentsNotValidException(notValid.get());
        }

        Optional<User> byUserIdentifier = this.userRepository.findByUserIdentifier(user.getUserIdentifier());

        if (byUserIdentifier.isPresent()) {
            throw new UserAlreadyRegisteredException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if (Objects.isNull(user.getRole())) {
            user.setRole(UserRole.USER);
        }

        this.userRepository.save(user);

    }
}
