package io.github.granekux.wieczornydecydent.services;

import io.github.granekux.wieczornydecydent.dto.UserDto;
import io.github.granekux.wieczornydecydent.model.UserRepository;
import org.springframework.stereotype.Service;

@Service
class AuthService implements  IAuthService {


    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String Register(UserDto user) {
        return "";
    }

    @Override
    public String Login(UserDto user) {
        return "";
    }
}
