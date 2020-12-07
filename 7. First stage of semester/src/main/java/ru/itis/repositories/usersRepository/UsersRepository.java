package ru.itis.repositories.usersRepository;

import ru.itis.model.User;
import ru.itis.repositories.CrudRepository;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<User> {
    Optional<User> findByEmail(String email);
    Boolean updateImageId(Long userId, Long imageId);
    Boolean updateEmail(String oldNew, String newEmail);
    Boolean updateName(String oldName, String newName);
    Boolean updateSurname(String oldSurname, String newSurname);
}
