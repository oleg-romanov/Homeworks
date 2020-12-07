package ru.itis.dto;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Long imageId;
}
