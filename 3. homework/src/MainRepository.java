import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Random;


public class MainRepository {

    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "bmwm5";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/IndexExample";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(connection);

//      List<User> users = usersRepository.findAll();
//        List<User> users = usersRepository.findAllByAge(19);
//      users.forEach(user -> System.out.println(user.getAge()));
//        users.forEach(user -> System.out.println(user.getFirstName()));

        for (int i = 0; i < 500000; i++) {
            String phone = String.valueOf((int) (Math.random() * 1000000000));
            usersRepository.addUser(phone);
        }
    }
}
