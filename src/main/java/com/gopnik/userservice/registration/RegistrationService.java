package com.gopnik.userservice.registration;

import com.gopnik.userservice.appuser.AppUser;
import com.gopnik.userservice.appuser.AppUserRole;
import com.gopnik.userservice.appuser.AppUserService;
import com.gopnik.userservice.util.RequestValidation;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final AppUserService appUserService;

    public String register(RegistrationRequest request){
        boolean isValid = RequestValidation.validateEmail(request.getEmail());
        if(!isValid)
        {
            throw new IllegalStateException(("Email is not valid"));
        }

        return appUserService.signUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER
                        ));
    }
}
