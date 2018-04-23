package com.inovatrend.matijaApp.service;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import com.inovatrend.matijaApp.dao.UserRepository;
import com.inovatrend.matijaApp.domain.User;

import java.util.List;
import java.util.Optional;


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
    public List<User> getAllUsers()
    {
        Sort sort = new Sort(Sort.Direction.ASC, "lastName");
        return userRepository.findAll(sort);
    }

    @Override
    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }





}
