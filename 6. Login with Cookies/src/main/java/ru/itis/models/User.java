package ru.itis.models;

import lombok.*;

@Builder
@ToString
@Setter
@Getter
@EqualsAndHashCode
public class User {
    private int id;
    private String login;
    private String password;
}
