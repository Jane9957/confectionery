package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import services.Customer;
import services.entities.Profile;

@Controller
public class ProfileController {

    @Autowired
    private Customer service;

    @GetMapping("/profile")
    public String profile(Model model) {
        System.out.println("LOL");

        Profile profile = service.getProfile();
        model.addAttribute("id", profile.getId());
        model.addAttribute("login", profile.getLogin());
        model.addAttribute("password", profile.getPassword());


        return "profile";
    }

}
