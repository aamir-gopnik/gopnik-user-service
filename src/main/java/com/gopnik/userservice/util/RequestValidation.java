package com.gopnik.userservice.util;

import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class RequestValidation {

    public static boolean validateEmail(String email){
        return true;
        //return email.matches("/^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$/");
    }
}
