package ru.itis.models;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Integer age;

    public User (String firsName, String lastname, Integer age){
        this.firstName = firsName;
        this.lastName = lastname;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

}
