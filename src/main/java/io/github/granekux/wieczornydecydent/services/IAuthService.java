package io.github.granekux.wieczornydecydent.services;

import io.github.granekux.wieczornydecydent.dto.UserDto;
import io.github.granekux.wieczornydecydent.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface IAuthService {

    void Register(UserDto user) throws NotFoundException;

    String Login(UserDto userDto) throws NotFoundException;

}
