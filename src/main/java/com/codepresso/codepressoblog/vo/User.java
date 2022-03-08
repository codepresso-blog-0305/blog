package com.codepresso.codepressoblog.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@ToString
@Setter
@Getter
@AllArgsConstructor
public class User {
    Integer id;

    @NotNull(message = "email should not be null")
    @NotBlank(message = "email should not be blank")
    @Email
    String email;

    @NotNull(message = "name should not be null")
    @NotBlank(message = "name should not be blank")
    String name;

    @NotNull(message = "password should not be null")
    @NotBlank(message = "password should not be blank")
    String password;

    Date createAt;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public User(String name, Integer userId) {
        this.name = name;
        this.id = userId;
    }

}
