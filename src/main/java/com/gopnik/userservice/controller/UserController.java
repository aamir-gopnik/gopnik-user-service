package com.gopnik.userservice.controller;

import com.gopnik.userservice.appuser.AppUser;
import com.gopnik.userservice.registration.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private RegistrationService registrationService;

    //Created this controller to verify whether Authentication is working or not
    //After Authentication this controller will work if JWT is sent in header
    @GetMapping("/checkAuthWorking")
    public String checkAuthWorking(Principal principal){
        return "Auth working fine : Logged in " + principal.getName();
    }

    @GetMapping("/getUsers/{name}")
    public List<AppUser> getUsersWithFirstName(@PathVariable String firstName)
    {
        return registrationService.getUserByFirstName(firstName);
    }




}
