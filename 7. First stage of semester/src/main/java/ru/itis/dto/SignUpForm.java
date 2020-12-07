package ru.itis.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * 23.10.2020
 * 4. Simple Web Application
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Data
public class SignUpForm {
    @NotEmpty
    @Length(min = 2)
    private String firstName;

    @NotEmpty
    @Length(min = 2)
    private String lastName;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Length(max = 18)
    private String phoneNumber;

    @NotEmpty
    @Length(min = 8, max = 40)
    private String password;
}
