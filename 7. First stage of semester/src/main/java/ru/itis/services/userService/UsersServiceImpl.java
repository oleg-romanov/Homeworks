package ru.itis.services.userService;

import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import java.util.Optional;

public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        Optional<User> user = usersRepository.findByEmail(email);
        UserDto dto = UserDto.builder()
                .email(email)
                .firstName(user.get().getFirstName())
                .lastName(user.get().getLastName())
                .build();
        return dto;
    }
}
