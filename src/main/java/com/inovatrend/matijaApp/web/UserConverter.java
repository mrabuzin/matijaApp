package com.inovatrend.matijaApp.web;

import com.inovatrend.matijaApp.domain.User;
import com.inovatrend.matijaApp.service.UserManager;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class UserConverter implements Converter<String, User> {

    public final UserManager userManager;

    public UserConverter(UserManager userManager) {
        this.userManager = userManager;
    }

    @Override
    public User convert(String userId) {
        Long id = Long.parseLong(userId);

        Optional<User> userOptional =  userManager.getUser(id);
        return userOptional.orElse(null);
    }


}
