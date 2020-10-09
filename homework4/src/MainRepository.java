import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;

/**
 * 25.09.2020
 * 2. DB
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class MainRepository {

    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "bmwm5";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11-906";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(connection);

//      List<User> users = usersRepository.findAll();
        List<User> users = usersRepository.findAllByAge(19);
//      users.forEach(user -> System.out.println(user.getAge()));
        users.forEach(user -> System.out.println(user.getFirstName()));
    }
}
