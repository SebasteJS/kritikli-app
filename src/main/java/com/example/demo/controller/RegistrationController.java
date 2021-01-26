package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    @GetMapping("/main/registration")
    public String registrationPage() {
        return "registration";
    }

    //rejestracja użytkownika, jego zapis do bazy
}
