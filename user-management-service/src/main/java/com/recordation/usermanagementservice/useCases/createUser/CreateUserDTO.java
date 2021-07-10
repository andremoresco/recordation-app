package com.recordation.usermanagementservice.useCases.createUser;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUserDTO {

    private String name;
    private String userIdentifier;
    private String email;
    private String password;

}
