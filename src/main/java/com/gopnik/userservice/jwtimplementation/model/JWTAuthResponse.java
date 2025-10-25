package com.gopnik.userservice.jwtimplementation.model;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JWTAuthResponse {

    private String userName;
    private String firstName;
    private String jwtToken;
}
