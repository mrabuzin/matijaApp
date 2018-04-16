package com.inovatrend.matijaApp.service;

import org.springframework.stereotype.Service;


import com.inovatrend.matijaApp.dao.UserRepository;
import com.inovatrend.matijaApp.domain.User;

import java.util.List;


@Service
public class UserManagerClass implements UserManager {

    private final UserRepository userRepository;

    public UserManagerClass(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
