package com.inovatrend.matijaApp.service;

import java.util.List;
import com.inovatrend.matijaApp.domain.User;

public interface UserManager {

    User save(User user);

    List<User> getAllUsers();

    void deleteUser(Long id);

}
