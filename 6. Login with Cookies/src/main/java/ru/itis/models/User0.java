package ru.itis.models;

import lombok.*;

@ToString
@Setter
@Getter
@Builder
@AllArgsConstructor
public class User0 {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Integer age;
}
