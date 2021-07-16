package com.recordation.usermanagementservice.useCases.authenticateUser;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationHttpResponse {

    private String token;

}
