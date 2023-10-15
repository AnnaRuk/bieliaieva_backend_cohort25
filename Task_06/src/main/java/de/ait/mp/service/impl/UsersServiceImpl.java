package de.ait.mp.service.impl;

import de.ait.mp.dto.user_account.NewUserDto;
import de.ait.mp.dto.user_account.UserDto;
import de.ait.mp.exceptions.RestException;
import de.ait.mp.models.User;
import de.ait.mp.repositories.UsersRepository;
import de.ait.mp.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

   private final UsersRepository usersRepository;
   private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto register(NewUserDto newUser) {

        if (usersRepository.existsByEmail(newUser.getEmail())){
            throw new RestException(HttpStatus.CONFLICT, "User with email <" + newUser.getEmail() + "> already exists");
        }

        User user = User.builder()
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .email(newUser.getEmail())
                .password(passwordEncoder.encode(newUser.getPassword()))
                .role(User.Role.USER)
                .build();

        usersRepository.save(user);
        return UserDto.from(user);
    }
}
