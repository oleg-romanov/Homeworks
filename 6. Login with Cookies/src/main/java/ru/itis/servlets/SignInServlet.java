package ru.itis.servlets;

import lombok.SneakyThrows;
import ru.itis.models.User;
import ru.itis.repositories.CookiesRepository;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11-906";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "bmwm5";

    private UsersRepository usersRepository;
    private CookiesRepository cookiesRepository;

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
            cookiesRepository = new UsersRepositoryJdbcImpl(connection, statement);
        } catch (SQLException throwables) {
            throw  new IllegalStateException(throwables);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/html/profile.html").forward(request, response);
    }

    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        Optional<User> user = cookiesRepository.findByLogin(login);
        if (user.get().getLogin() == null) {
            response.sendRedirect("/signIn");
            return;
        }
        if (user.get().getPassword().equals(password)) {
                String uniqueKey = UUID.randomUUID().toString();
                Cookie auth = new Cookie("Auth", uniqueKey);
                cookiesRepository.saveCookie(user.get(), auth);
        } else {
            response.sendRedirect("/signIn");
        }
    }
}
