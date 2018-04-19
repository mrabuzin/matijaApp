package com.inovatrend.matijaApp.service;

import java.util.List;
import java.util.Optional;

import com.inovatrend.matijaApp.domain.Sex;
import com.inovatrend.matijaApp.domain.User;

public interface UserManager {

    User save(User user);

    List<User> getAllUsers();

    Optional<User> getUser(Long id);

    void deleteUser(Long id);



}
