package ru.itis.services.signInServices;

import ru.itis.dto.SignInForm;

public interface SignInService {
    boolean signIn(SignInForm form);
}
