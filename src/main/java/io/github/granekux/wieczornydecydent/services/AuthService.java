package io.github.granekux.wieczornydecydent.services;

import io.github.granekux.wieczornydecydent.dto.UserDto;
import io.github.granekux.wieczornydecydent.exceptions.NotFoundException;
import io.github.granekux.wieczornydecydent.model.User;
import io.github.granekux.wieczornydecydent.model.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements IAuthService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public void Register(UserDto user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            throw new NotFoundException("Email " + user.getEmail() + " już jest zajęty!");
        }

        String hashedPassword = passwordEncoder.encode(user.getPassword());


        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setPassword(hashedPassword);

        userRepository.save(newUser);

//        return "Tu będzie wygenerowany token JWT dla " + user.getEmail();
    }

    @Override
    public String Login(UserDto userDto) {

        User user = userRepository.findByEmail(userDto.getEmail())
                .orElseThrow(() -> new NotFoundException("Błędny email lub hasło!"));

        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new NotFoundException("Błędny email lub hasło!");
        }

        return jwtService.generateToken(user.getEmail());
    }

}
