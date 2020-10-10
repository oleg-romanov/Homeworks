package ru.itis.repositories;

import ru.itis.models.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private Connection connection;

    private Statement statement;

    private static final String SQL_INSERT_INTO_DRIVER = "insert into driver(first_name, last_name, age) values ";

    public UsersRepositoryJdbcImpl(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    public void save(User entity) {
        String sql = SQL_INSERT_INTO_DRIVER + "('" + entity.getFirstName() + "', '" + entity.getLastName() +
                "', " + entity.getAge() + ");";
        try {
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
