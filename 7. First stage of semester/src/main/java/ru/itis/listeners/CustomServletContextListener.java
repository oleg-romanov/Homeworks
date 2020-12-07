package ru.itis.listeners;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.repositories.categoryRepository.CategoryRepository;
import ru.itis.repositories.categoryRepository.CategoryRepositoryJdbcImpl;
import ru.itis.repositories.filesRepository.ImagesRepository;
import ru.itis.repositories.filesRepository.ImagesRepositoryImpl;
import ru.itis.repositories.productRepository.ProductRepository;
import ru.itis.repositories.productRepository.ProductRepositoryJdbcImpl;
import ru.itis.repositories.usersRepository.UsersRepository;
import ru.itis.repositories.usersRepository.UsersRepositoryJdbcImpl;
import ru.itis.services.fileUpdoadService.FileUploadService;
import ru.itis.services.fileUpdoadService.FileUploadServiceImpl;
import ru.itis.services.signInServices.SignInService;
import ru.itis.services.signInServices.SignInServiceImpl;
import ru.itis.services.signUpServices.SignUpService;
import ru.itis.services.signUpServices.SignUpServiceImpl;
import ru.itis.services.userService.UsersService;
import ru.itis.services.userService.UsersServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

@WebListener
public class CustomServletContextListener implements ServletContextListener {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/OnlineShop";
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

        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();

        CategoryRepository categoryRepository = new CategoryRepositoryJdbcImpl(dataSource);
        ProductRepository productRepository = new ProductRepositoryJdbcImpl(dataSource, categoryRepository);
        UsersRepository usersRepository = new UsersRepositoryJdbcImpl(dataSource);
        ImagesRepository imagesRepository = new ImagesRepositoryImpl(dataSource);

        SignUpService signUpService = new SignUpServiceImpl(usersRepository);
        SignInService signInService = new SignInServiceImpl(usersRepository);
        UsersService usersService = new UsersServiceImpl(usersRepository);
        FileUploadService fileUploadService = new FileUploadServiceImpl(imagesRepository);

        servletContext.setAttribute("productRepository", productRepository);
        servletContext.setAttribute("categoryRepository", categoryRepository);
        servletContext.setAttribute("signUpService", signUpService);
        servletContext.setAttribute("signInService", signInService);
        servletContext.setAttribute("usersService", usersService);
        servletContext.setAttribute("usersRepository", usersRepository);
        servletContext.setAttribute("validator", validator);
        servletContext.setAttribute("filesUploadService", fileUploadService);
}

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
