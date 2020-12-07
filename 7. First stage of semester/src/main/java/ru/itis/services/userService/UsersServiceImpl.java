package ru.itis.services.userService;

import ru.itis.dto.UserDto;
import ru.itis.model.User;
import ru.itis.repositories.usersRepository.UsersRepository;

import java.util.Optional;

public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;

    public UsersServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        Optional<User> user = usersRepository.findByEmail(email);
        if(user.isPresent() == false) {
            return UserDto.builder().build();
        }
        UserDto dto = UserDto.builder()
                .id(user.get().getId())
                .email(email)
                .firstName(user.get().getFirstName())
                .lastName(user.get().getLastName())
                .imageId(user.get().getImageId())
                .build();
        return dto;
    }

    @Override
    public Boolean updateDataOfUser(UserDto newDto, UserDto oldDto) {
        boolean isChange = false;
        if (!newDto.getEmail().equals("")) {
            usersRepository.updateEmail(oldDto.getEmail(), newDto.getEmail());
            isChange = true;
        }
        if (!newDto.getFirstName().equals("")) {
            usersRepository.updateName(oldDto.getFirstName(), newDto.getFirstName());
            isChange = true;
        }
        if (!newDto.getLastName().equals("")) {
            usersRepository.updateSurname(oldDto.getLastName(), newDto.getLastName());
            isChange = true;
        }
        return isChange;
    }


}
