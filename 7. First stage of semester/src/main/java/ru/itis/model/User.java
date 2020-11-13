package ru.itis.model;

import ru.itis.model.entity.Entity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User extends Entity {
    private String name;
    private String firstName;
    private String lastName;
    private String email;
    private String hashPassword;
}
