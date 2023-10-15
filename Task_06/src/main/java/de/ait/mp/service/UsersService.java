package de.ait.mp.service;

import de.ait.mp.dto.user_account.NewUserDto;
import de.ait.mp.dto.user_account.UserDto;

public interface UsersService {


    UserDto register(NewUserDto newUser);

}
