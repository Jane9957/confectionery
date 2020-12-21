package org.example.confectionery.services.validation;

import org.example.confectionery.dataBase.DataBaseConnect;
import org.example.confectionery.dataBase.DataBaseValidation;
import org.example.confectionery.web.controllers.forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CreateUserValidation {

    @Autowired
    private DataBaseValidation dataBaseValidation;

    public boolean checkRegistrationForm(RegistrationForm form) {

        boolean name = false;
        try {
            name = dataBaseValidation.checkLogin(form.getLogin());
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        if (!name) {
            return false;
        }
        if (!form.getPassword().equals(form.getPasswordConfirm())) {
            return false;
        }
        if (form.getName_first() == null || form.getName_first().trim().equals("")) {
            return false;
        }
        if (form.getName_middle() == null || form.getName_middle().trim().equals("")) {
            return false;
        }
        if (form.getName_last() == null || form.getName_last().trim().equals("")) {
            return false;
        }
        if (form.getEmail() == null || validateEmail(form.getEmail().trim())) {
            return false;
        }
        if (form.getPhone() == null || validatePhone(form.getPhone())) {
            return false;
        }
        return true;
    }

    private boolean validateEmail(String email) {
        Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = emailPattern.matcher(email);
        return !matcher.matches();
    }

    private boolean validatePhone(String phone) {
        Pattern pattern = Pattern.compile("^\\d{11}$");
        Matcher matcher = pattern.matcher(phone);
        return !matcher.matches();
    }

}
