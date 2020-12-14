package services;

import dataBase.DataBaseConnect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.entities.Profile;

@Service
public class Customer {

    @Autowired
    private DataBaseConnect dataBaseConnect;

    public Profile getProfile() {
        Profile profile = new Profile();

        try {
            profile = dataBaseConnect.main();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return profile;
    }

}
