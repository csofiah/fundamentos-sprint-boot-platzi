package com.fundamentosplatzi.sprintboot.fundamentos.usecase;

import com.fundamentosplatzi.sprintboot.fundamentos.entity.User;
import com.fundamentosplatzi.sprintboot.fundamentos.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {
    private UserService userService;

    public CreateUser(UserService userService){
        this.userService = userService;
    }

    public User save(User newUser) {
        return userService.save(newUser);
    }
}
