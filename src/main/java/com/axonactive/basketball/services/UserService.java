package com.axonactive.basketball.services;

import com.axonactive.basketball.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
}
