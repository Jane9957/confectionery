package org.example.confectionery.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.example.confectionery.services.Customer;
import org.example.confectionery.services.entities.Profile;

@Controller
public class ProfileController {

    @Autowired
    private Customer service;

    @GetMapping("/profile")
    public String profile(Model model) {

        Profile profile = service.getProfile();
        model.addAttribute("id", profile.getId());
        model.addAttribute("login", profile.getLogin());
        model.addAttribute("password", profile.getPassword());
        model.addAttribute("name_first", profile.getName_first());
        model.addAttribute("name_middle", profile.getName_middle());
        model.addAttribute("name_last", profile.getName_last());
        model.addAttribute("email", profile.getEmail());
        model.addAttribute("phone", profile.getPhone());
        model.addAttribute("birthday", profile.getBirthday());
        model.addAttribute("company", profile.getCompany());


        return "profile";
    }

}
