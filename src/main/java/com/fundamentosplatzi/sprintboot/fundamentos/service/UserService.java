package com.fundamentosplatzi.sprintboot.fundamentos.service;

import com.fundamentosplatzi.sprintboot.fundamentos.entity.User;
import com.fundamentosplatzi.sprintboot.fundamentos.repository.UserRepository;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserService {
    private final Log LOGGER = LogFactory.getLog(UserService.class);
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //si existe un error en el insert va a ser rollback
    @Transactional
    public void  saveTransactional(List<User> users){
        users.stream()
                .peek(user -> LOGGER.info("usuario insertado :"+user))
                .forEach(userRepository::save);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
