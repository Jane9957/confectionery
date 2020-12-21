package org.example.confectionery.services;

import org.example.confectionery.dataBase.DataBaseConnect;
import org.example.confectionery.dataBase.DataBaseLoger;
import org.example.confectionery.web.controllers.forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class RegistrationService {

    @Autowired
    DataBaseConnect dataBaseConnect;

    @Autowired
    DataBaseLoger dataBaseLoger;

    @Autowired
    PasswordEncoder passwordEncoder;


    public boolean createUser(RegistrationForm registrationForm) {
        try {
            registrationForm.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
            dataBaseConnect.createUser(registrationForm);

            dataBaseLoger.newLogertext("New customer have been added - " + registrationForm.getLogin() + "  At  " + dataBaseLoger.GetDateTime());
        } catch (SQLException exception) {
            System.out.println(exception);
        }
        return true;
    }

}
