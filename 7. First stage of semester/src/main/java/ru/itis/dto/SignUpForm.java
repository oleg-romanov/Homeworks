package ru.itis.dto;

import lombok.Data;

/**
 * 23.10.2020
 * 4. Simple Web Application
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Data
public class SignUpForm {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
