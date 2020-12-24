import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UsersRepositoryJdbcImpl implements UsersRepository {

    private Connection connection;

    private static final String SQL_SELECT_ALL_FROM_DRIVER = "select * from driver";
    private static final String SQL_SELECT_BY_AGE_FROM_DRIVER = "select * from driver where age = ";

    //language=SQL
    private static final String SQL_ADD_NEW_USER_WITH_INDEX = "insert into users_with_index(phone) values (?)";
    private static final String SQL_ADD_NEW_USER_WITH_OUT_INDEX = "insert into users_without_index (phone) values (?)";

    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAllByAge(Integer age) {
        //TODO: реализовать (ИСПРАВЛЕНО)
        try {
         //   PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_AGE_FROM_DRIVER);
         //   statement.setInt(1, age);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_BY_AGE_FROM_DRIVER + age);
        //    ResultSet resultSet = statement.executeQuery();

            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age")
                );
                result.add(user);
            }
            if (result.isEmpty()) {
                System.out.println("По введенному возврасту ничего не найдено...");
            }
            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void addUser(String phone) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_ADD_NEW_USER_WITH_INDEX);
            PreparedStatement statement1 = connection.prepareStatement(SQL_ADD_NEW_USER_WITH_OUT_INDEX);
            statement.setString(1, phone);
            statement1.setString(1, phone);
            statement.executeUpdate();
            statement1.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<User> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL_FROM_DRIVER);

            List<User> result = new ArrayList<>();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"));
                result.add(user);
            }

            return result;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void remove(User entity) {

    }

    @Override
    public void removeById(Long id) {

    }
}
