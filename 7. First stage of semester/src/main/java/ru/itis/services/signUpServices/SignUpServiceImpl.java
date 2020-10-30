package ru.itis.services.signUpServices;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.SignUpForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;
import ru.itis.services.signUpServices.SignUpService;

/**
 * 23.10.2020
 * 4. Simple Web Application
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class SignUpServiceImpl implements SignUpService {

    private UsersRepository usersRepository;

    private PasswordEncoder passwordEncoder;

    public SignUpServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public void signUp(SignUpForm form) {
        User user = User.builder()
                .firstName(form.getFirstName())
                .lastName(form.getLastName())
                .email(form.getEmail())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .build();

        usersRepository.save(user);
        System.out.println(user);
    }
}
