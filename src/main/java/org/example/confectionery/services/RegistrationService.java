package org.example.confectionery.services;

import org.example.confectionery.dataBase.DataBaseConnect;
import org.example.confectionery.web.controllers.forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class RegistrationService {

    @Autowired
    DataBaseConnect dataBaseConnect;

    public boolean createUser(RegistrationForm registrationForm) {
        try {
            dataBaseConnect.createUser(registrationForm);
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return true;
    }

}
