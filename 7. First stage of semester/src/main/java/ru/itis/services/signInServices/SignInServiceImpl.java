package ru.itis.services.signInServices;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.SignInForm;
import ru.itis.models.User;
import ru.itis.repositories.UsersRepository;

import javax.servlet.http.HttpSession;
import java.util.Optional;

public class SignInServiceImpl implements SignInService {

    private UsersRepository usersRepository;

    private PasswordEncoder passwordEncoder;

    public SignInServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public boolean signIn(SignInForm form) {
        String email = form.getEmail();
        String password = form.getPassword();
        System.out.println(password);
        Optional<User> user = usersRepository.findByEmail(email);
        if (user.get().getEmail() == null) {
            return false;
        }

        if (passwordEncoder.matches(password, user.get().getHashPassword())) {
            return true;
        } else {
            return false;
        }
    }
}
