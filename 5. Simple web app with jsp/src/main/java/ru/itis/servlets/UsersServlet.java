package ru.itis.servlets;

import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11-906";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "bmwm5";

    private UsersRepository usersRepository;
    private List<User> users;

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }

        try {
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            Statement statement = connection.createStatement();
            usersRepository = new UsersRepositoryJdbcImpl(connection, statement);
        } catch (SQLException throwables) {
            throw  new IllegalStateException(throwables);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        users = usersRepository.findAll();
        request.setAttribute("usersForJsp", users);
        request.getRequestDispatcher("jsp/users.jsp").forward(request, response);
    }
}
