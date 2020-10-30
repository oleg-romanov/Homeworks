package ru.itis.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.ToString;

/**
 * 02.10.2020
 * 4. Simple Web Application
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String hashPassword;
}
