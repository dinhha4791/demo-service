package com.demoservice.service.user;

import com.demoservice.entity.User;
import com.demoservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        List<User> users = userRepository.findAll();

        return users;
    }
}
