package ru.itis.listeners;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.repositories.UsersRepository;
import ru.itis.repositories.UsersRepositoryJdbcImpl;
import ru.itis.services.signInServices.SignInService;
import ru.itis.services.signInServices.SignInServiceImpl;
import ru.itis.services.signUpServices.SignUpService;
import ru.itis.services.signUpServices.SignUpServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 23.10.2020
 * 4. Simple Web Application
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@WebListener
public class CustomServletContextListener implements ServletContextListener {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/11-906";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "bmwm5";
    private static final String DB_DRIVER = "org.postgresql.Driver";

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(DB_DRIVER);
        dataSource.setUsername(DB_USERNAME);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setUrl(DB_URL);

        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        SignUpService signUpService = new SignUpServiceImpl(usersRepository);
        SignInService signInService = new SignInServiceImpl(usersRepository);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("signInService", signInService);
}

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
