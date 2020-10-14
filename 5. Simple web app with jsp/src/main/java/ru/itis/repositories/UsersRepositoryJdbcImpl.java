package ru.itis.repositories;

import ru.itis.models.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    private Connection connection;

    private Statement statement;

    private static final String SQL_INSERT_INTO_DRIVER = "insert into driver(first_name, last_name, age) values ";
    private static final String SQL_SELECT_ALL_FROM_DRIVAER = "select * from driver";


    public UsersRepositoryJdbcImpl(Connection connection, Statement statement) {
        this.connection = connection;
        this.statement = statement;
    }

    public List<User> findAll() {
        try {
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_DRIVAER);
            List<User> result = new ArrayList<User>();;
            while (resultSet.next()) {
                User user = User.builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .age(resultSet.getInt("age"))
                        .build();
                result.add(user);
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
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
