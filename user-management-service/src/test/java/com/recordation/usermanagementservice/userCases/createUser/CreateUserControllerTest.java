package com.recordation.usermanagementservice.userCases.createUser;

import com.google.gson.Gson;
import com.recordation.usermanagementservice.security.JwtTokenProvider;
import com.recordation.usermanagementservice.useCases.createUser.CreateUserController;
import com.recordation.usermanagementservice.useCases.createUser.CreateUserDTO;
import com.recordation.usermanagementservice.useCases.createUser.CreateUserUseCase;
import com.recordation.usermanagementservice.useCases.findUserByIdentifier.FindUserByIdentifierUseCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CreateUserController.class)
public class CreateUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FindUserByIdentifierUseCase findUserByIdentifierUseCase;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private CreateUserUseCase createUserUseCase;

    @MockBean
    private PermissionEvaluator proxyTest;

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void handleCreateUserOk() throws Exception {
        CreateUserDTO createUserDTO = CreateUserDTO.builder()
                .name("Andre")
                .userIdentifier("08797124907")
                .email("teste@unit.com")
                .password("testunit")
                .build();
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .content(new Gson().toJson(createUserDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void handleCreateUserContentNull() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @WithMockUser(authorities = "ADMIN")
    public void handleCreateUserContentMissingNameInformation() throws Exception {
        CreateUserDTO createUserDTO = CreateUserDTO.builder()
                .userIdentifier("08797124907")
                .email("teste@unit.com")
                .password("testunit")
                .build();

        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(createUserDTO)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"error_code\": \"METHOD_ARGUMENT_NOT_VALID\", \"message\":[\"Name is required!\"]}"));
    }

    @Test
    @WithMockUser(authorities = "USER")
    public void handleCreateUserWithoutAdminAuthority() throws Exception {
        CreateUserDTO createUserDTO = CreateUserDTO.builder()
                .name("Andre")
                .userIdentifier("08797124907")
                .email("teste@unit.com")
                .password("testunit")
                .build();
        this.mockMvc.perform(
                MockMvcRequestBuilders.post("/users")
                        .content(new Gson().toJson(createUserDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isForbidden());
    }
}
