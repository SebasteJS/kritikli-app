package com.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/main/user-profile")
    public String userPage() {
        return "user-profile";
    }

    //wyświetlanie danych użytkownika
    //wyświetlanie postów uż
    //wyśw koment uż
    //ludzie którzy cię obserwują
    //ludzie obserwowani
    //wiadomości
}
