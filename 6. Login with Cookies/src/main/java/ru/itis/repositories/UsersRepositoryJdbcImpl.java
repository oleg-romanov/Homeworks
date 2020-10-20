package ru.itis.repositories;

import ru.itis.models.User;

import javax.servlet.http.Cookie;
import java.sql.*;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository, CookiesRepository{

    private Connection connection;

    private Statement statement;

    private static final String SQL_INSERT_INTO_USERS =
            "insert into users(login, password, first_name, last_name, age) values (?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_BY_LOGIN_FROM_USERS = "select * from users where login = ?";
    private static final String SQL_SELECT_BY_ID_FROM_COOKIES = "select * from cookies where user_id = ?";
    private static final String SQL_INSERT_INTO_COOKIES = "insert into cookies(uuid, user_id) values (?, ?)";
    private static final String SQL_UPDATE_INTO_COOKIES = "update cookies set uuid = ? where user_id = ?";

    public UsersRepositoryJdbcImpl(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    public void save(User entity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_INTO_COOKIES);

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Optional<User> findByLogin(String login) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_LOGIN_FROM_USERS);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            User user = User.builder().build();
            if (resultSet.next()) {
                 user = User.builder()
                        .id(resultSet.getInt("id"))
                        .login(resultSet.getString("login"))
                        .password(resultSet.getString("password"))
                        .build();
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void saveCookie(User entity, Cookie auth) throws SQLException, Exception {
        try {
            String sql;
            if (findByIdFromCookies(entity.getId())) {
                sql = SQL_UPDATE_INTO_COOKIES;
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, auth.getValue());
                preparedStatement.setInt(2, entity.getId());
                preparedStatement.executeUpdate();
            } else {
                sql = SQL_INSERT_INTO_COOKIES;
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, auth.getValue());
                preparedStatement.setInt(2, entity.getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public boolean findByIdFromCookies(int id) {
        boolean isThere = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID_FROM_COOKIES);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                isThere = true;
            }
            return isThere;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
