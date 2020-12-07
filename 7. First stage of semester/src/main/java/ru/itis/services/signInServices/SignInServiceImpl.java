package ru.itis.services.signInServices;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.SignInForm;
import ru.itis.model.User;
import ru.itis.repositories.usersRepository.UsersRepository;
import java.util.Optional;

public class SignInServiceImpl implements SignInService {

    private UsersRepository usersRepository;

    private PasswordEncoder passwordEncoder;

    public SignInServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public boolean signIn(SignInForm form, Boolean asAmin) {
        String email = form.getEmail();
        String password = form.getPassword();
        System.out.println(password);
        Optional<User> optionalUser = usersRepository.findByEmail(email);
        if(optionalUser.isPresent() == false) {
            return false;
        }
        User user = optionalUser.get();
        if (user.getIsAdmin() == false && asAmin) { return false; }
        if (user.getEmail() == null) {
            return false;
        }
        return passwordEncoder.matches(password, user.getHashPassword());
    }
}
