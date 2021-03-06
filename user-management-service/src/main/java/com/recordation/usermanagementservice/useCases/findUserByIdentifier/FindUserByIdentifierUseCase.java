package com.recordation.usermanagementservice.useCases.findUserByIdentifier;

import com.recordation.usermanagementservice.model.User;
import com.recordation.usermanagementservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FindUserByIdentifierUseCase implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public FindUserByIdentifierUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository.findByUserIdentifier(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found!", username)));
    }
}