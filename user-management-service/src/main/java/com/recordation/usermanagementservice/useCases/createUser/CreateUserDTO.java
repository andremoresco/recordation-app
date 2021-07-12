package com.recordation.usermanagementservice.useCases.createUser;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDTO {

    @NotBlank(message = "Name is required!")
    private String name;

    @NotBlank(message = "User Identifier is required!")
    private String userIdentifier;

    @NotBlank(message = "Email is required!")
    private String email;

    @NotBlank(message = "Password is required!")
    private String password;

}
