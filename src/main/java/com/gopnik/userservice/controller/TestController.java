package com.gopnik.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/home")
public class TestController {

    //Created this controller to verify whether Authentication is working or not
    //After Authentication this controller will work if JWT is sent in header
    @GetMapping("/checkAuthWorking")
    public String checkAuthWorking(Principal principal){
        return "Auth working fine : Logged in " + principal.getName();
    }


}
