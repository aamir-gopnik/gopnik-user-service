package com.gopnik.userservice.jwtimplementation.model;


import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class JWTResponse {

    private String jwtToken;

    private String username;


}
