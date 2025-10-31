package io.github.granekux.wieczornydecydent.services;

import io.github.granekux.wieczornydecydent.dto.UserDto;
import io.github.granekux.wieczornydecydent.exceptions.NotFoundException;
import io.github.granekux.wieczornydecydent.model.User;
import io.github.granekux.wieczornydecydent.model.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService {


    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void Register(UserDto user) throws NotFoundException {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            throw new NotFoundException("Email " + user.getEmail() + " już jest zajęty!");
        }

        // TODO: Hashowanie hasła (BARDZO WAŻNE, zrobimy zaraz)
        String hashedPassword = user.getPassword();


        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(hashedPassword);

        userRepository.save(newUser);

//        return "Tu będzie wygenerowany token JWT dla " + user.getEmail();
    }

    @Override
    public String Login(UserDto userDto) throws NotFoundException {

        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new NotFoundException("Błędny email lub hasło!"));

        // TODO: Sprawdzanie hasła (BARDZO WAŻNE)
        // Musimy porównać hasło z DTO z hasłem (hashem) w bazie

        // if(passwordEncoder.matches(userDto.getPassword(), user.getPassword())) { ... }

        // TODO: Generowanie tokenu JWT
        return "Tu będzie wygenerowany token JWT dla " + user.getEmail();
    }

}
