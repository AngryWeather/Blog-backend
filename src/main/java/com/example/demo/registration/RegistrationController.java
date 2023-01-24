package com.example.demo.registration;

import com.example.demo.appuser.AppUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute AppUser appUser) {
        registrationService.register(appUser);
        return "redirect:/login";
    }
}
