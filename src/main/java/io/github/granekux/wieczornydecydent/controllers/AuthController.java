package io.github.granekux.wieczornydecydent.controllers;

import io.github.granekux.wieczornydecydent.dto.UserDto;
import io.github.granekux.wieczornydecydent.exceptions.NotFoundException;
import io.github.granekux.wieczornydecydent.services.AuthService;
import io.github.granekux.wieczornydecydent.services.IAuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/auth")
class AuthController {

    private IAuthService authService;

    public AuthController(IAuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDto user) {

        authService.Register(user);


        return "Użytkownik zarejestrowany!";
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto user) {
        return authService.Login(user);
    }
}
