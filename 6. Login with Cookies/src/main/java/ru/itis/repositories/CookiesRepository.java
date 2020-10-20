package ru.itis.repositories;

import ru.itis.models.User;

import javax.servlet.http.Cookie;
import java.sql.SQLException;
import java.util.Optional;

public interface CookiesRepository extends CrudRepository<User> {
    Optional<User> findByLogin(String login);
    void saveCookie(User entity, Cookie auth) throws SQLException, Exception;
}
