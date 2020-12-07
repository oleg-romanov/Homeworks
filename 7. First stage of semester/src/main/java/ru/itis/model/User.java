package ru.itis.model;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User {
    private Long id;
    private String name;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String hashPassword;
    private Long imageId;
    private Boolean isAdmin;
}
