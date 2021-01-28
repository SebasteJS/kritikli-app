package com.example.demo.controller;

import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/main/registration")
    public String registrationPage(Model model) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping("/main/registration")
    public String registration(Model model, @ModelAttribute("user") @Valid UserDto user, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()) {
            userService.add(user);
            return "login";
        }
        model.addAttribute("users", new UserDto());
        return "registration";
    }

    //rejestracja użytkownika, jego zapis do bazy
}
