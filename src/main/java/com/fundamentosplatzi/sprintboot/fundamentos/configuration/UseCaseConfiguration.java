package com.fundamentosplatzi.sprintboot.fundamentos.configuration;

import com.fundamentosplatzi.sprintboot.fundamentos.service.UserService;
import com.fundamentosplatzi.sprintboot.fundamentos.usecase.GetUser;
import com.fundamentosplatzi.sprintboot.fundamentos.usecase.GetUserImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfiguration {

    @Bean
    GetUser getUser(UserService userService){
        return new GetUserImpl(userService);
    }
}
