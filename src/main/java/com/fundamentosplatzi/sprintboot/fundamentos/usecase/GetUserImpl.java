package com.fundamentosplatzi.sprintboot.fundamentos.usecase;

import com.fundamentosplatzi.sprintboot.fundamentos.entity.User;
import com.fundamentosplatzi.sprintboot.fundamentos.service.UserService;

import java.util.List;

public class GetUserImpl implements GetUser{
    private UserService userService;

    public GetUserImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUsers();
    }
}
