package org.example.confectionery.web.controllers;

import org.example.confectionery.services.CompanyService;
import org.example.confectionery.services.RegistrationService;
import org.example.confectionery.services.entities.Company;
import org.example.confectionery.services.validation.CreateUserValidation;
import org.example.confectionery.web.controllers.forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;

@Controller
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;

    @Autowired
    CompanyService companyService;

    @Autowired
    CreateUserValidation createUserValidation;

    @GetMapping("/registration")
    public String registration(Model model, RegistrationForm registrationForm) throws SQLException {
            model.addAttribute("error", false);
            model.addAttribute("companies", companyService.getCompanies());
            model.addAttribute("registrationForm", registrationForm);
            return "registration";
    }

    @PostMapping("/registration")
    public String registrationPost(Model model, @ModelAttribute("registrationForm") RegistrationForm registrationForm) {
        if(!createUserValidation.checkRegistrationForm(registrationForm)) {
            model.addAttribute("error", true);
            model.addAttribute("companies", companyService.getCompanies());
            model.addAttribute("registrationForm", registrationForm);
            return "registration";
        } else {
            registrationService.createUser(registrationForm);
            return "redirect:/login";
        }

    }

}
