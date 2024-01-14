package com.gopnik.userservice.jwtimplementation.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class JWTRequest {

    private String email;

    private String password;
}
