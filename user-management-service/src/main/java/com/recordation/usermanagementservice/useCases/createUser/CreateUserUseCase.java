package com.recordation.usermanagementservice.useCases.createUser;

import com.recordation.usermanagementservice.exceptions.UserAlreadyRegisteredException;
import com.recordation.usermanagementservice.model.User;
import com.recordation.usermanagementservice.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Slf4j
public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public CreateUserUseCase(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void execute(User user) throws UserAlreadyRegisteredException {

        Optional<User> byUserIdentifier = this.userRepository.findByUserIdentifier(user.getUserIdentifier());

        if (byUserIdentifier.isPresent()) {
            throw new UserAlreadyRegisteredException();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepository.save(user);

    }
}
