package ru.itis.models;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@ToString
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;
}
