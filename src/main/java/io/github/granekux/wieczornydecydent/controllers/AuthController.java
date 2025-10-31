package io.github.granekux.wieczornydecydent.controllers;

import io.github.granekux.wieczornydecydent.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/auth")
class AuthController {

    @PostMapping("/register")
    public String registerUser(@RequestBody UserDto user) {
        // TODO:
        // 1. Sprawdź, czy email już nie istnieje w bazie
        // 2. ZAHASHUJ hasło (użyj PasswordEncoder)
        // 3. Zapisz nowego użytkownika (User) w bazie danych

        return "Użytkownik zarejestrowany!"; // Zwróć sensowną odpowiedź
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto user) {
        // TODO:
        // 1. Znajdź użytkownika w bazie po emailu
        // 2. Porównaj hasło z DTO z hashem w bazie (użyj PasswordEncoder)
        // 3. Jeśli hasła się zgadzają -> WYGENERUJ TOKEN JWT
        // 4. Zwróć token JWT do klienta (np. w JSONie)

        return "Zalogowano pomyślnie! (tu będzie token JWT)";
    }
}
