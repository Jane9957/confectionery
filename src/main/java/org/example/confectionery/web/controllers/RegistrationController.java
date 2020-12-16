package org.example.confectionery.web.controllers;

import org.example.confectionery.services.RegistrationService;
import org.example.confectionery.web.controllers.forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class RegistrationController {

    //@Autowired
    //RegistrationService registrationService;

    @GetMapping("/registration")
    public String registration(Model model, RegistrationForm registrationForm) {

        model.addAttribute("registrationForm", registrationForm);
        return "registration";
    }


}
