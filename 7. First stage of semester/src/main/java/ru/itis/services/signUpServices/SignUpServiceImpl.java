package ru.itis.services.signUpServices;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.SignUpForm;
import ru.itis.model.User;
import ru.itis.repositories.usersRepository.UsersRepository;


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
//                .phoneNumber(form.getPhoneNumber())
                .hashPassword(passwordEncoder.encode(form.getPassword()))
                .isAdmin(false)
                .build();

        usersRepository.save(user);
        System.out.println(user);
    }
}
