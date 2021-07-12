package com.recordation.usermanagementservice.userCases.createUser;

import com.recordation.usermanagementservice.exceptions.UserAlreadyRegisteredException;
import com.recordation.usermanagementservice.exceptions.UserArgumentsNotValidException;
import com.recordation.usermanagementservice.model.User;
import com.recordation.usermanagementservice.repository.UserRepository;
import com.recordation.usermanagementservice.useCases.createUser.CreateUserUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserUseCaseTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private CreateUserUseCase createUserUseCase;

    @Test
    public void createUserSuccess() throws Exception {
        User user = new User(null, "Andre Moresco", "unit_test", "unit@test.com", null, "unit_test");
        Mockito.when(passwordEncoder.encode(user.getPassword())).thenReturn(user.getPassword() + "_encoded");
        this.createUserUseCase.execute(user);

    }

    @Test(expected = UserArgumentsNotValidException.class)
    public void throwUserArgumentsNotValidWhenFieldsNoFilled() throws Exception {
        User user = new User();
        this.createUserUseCase.execute(user);
    }

    @Test(expected = UserAlreadyRegisteredException.class)
    public void throwUserAlreadyRegisteredWhenFoundAUserWithTheSameIdentifier() throws Exception {
        User user = User.builder()
                .name("Andre")
                .userIdentifier("identifier_1")
                .email("unit@test.com")
                .password("unit_test")
                .build();


        Mockito.when(this.userRepository.findByUserIdentifier(user.getUserIdentifier())).thenReturn(Optional.of(User.builder().userIdentifier("identifier_1").build()));

        this.createUserUseCase.execute(user);
    }

}
