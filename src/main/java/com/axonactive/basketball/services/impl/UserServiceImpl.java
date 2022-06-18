package com.axonactive.basketball.services.impl;

import com.axonactive.basketball.entities.User;
import com.axonactive.basketball.repositories.UserRepository;
import com.axonactive.basketball.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
