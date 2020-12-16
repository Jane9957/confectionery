package org.example.confectionery.services;

import org.example.confectionery.dataBase.DataBaseConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.confectionery.services.entities.Profile;

@Service
public class Customer {

    @Autowired
    private DataBaseConnect dataBaseConnect;

    public Profile getProfile() {
        Profile profile = new Profile();

        try {
            profile = dataBaseConnect.getProfile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return profile;
    }

}
