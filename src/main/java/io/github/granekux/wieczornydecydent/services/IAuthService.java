package io.github.granekux.wieczornydecydent.services;

import io.github.granekux.wieczornydecydent.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
interface IAuthService {

    String Register(UserDto user);

    String Login(UserDto user);

}
