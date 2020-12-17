package org.example.confectionery.services;

import org.example.confectionery.dataBase.DataBaseConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.example.confectionery.services.entities.Profile;

import java.sql.SQLException;

@Service
public class User {

    @Autowired
    private DataBaseConnect dataBaseConnect;

    public Profile getProfile() {
        Profile profile = new Profile();

        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        try {
            profile = dataBaseConnect.getProfileByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return profile;
    }

    public Profile getProfileById(String id) {
        Profile profile = new Profile();
        try {
            profile = dataBaseConnect.getProfileById(Integer.valueOf(id));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return profile;
    }
}
