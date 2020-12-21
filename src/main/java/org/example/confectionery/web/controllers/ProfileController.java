package org.example.confectionery.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.example.confectionery.services.ProfileService;
import org.example.confectionery.services.entities.Profile;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProfileController {

    @Autowired
    private ProfileService service;

    @GetMapping("/profile")
    public String profile(Model model) {
        String role = "ROLE_FACTORY";
        for(GrantedAuthority grantedAuthority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
            role = grantedAuthority.getAuthority();
        }
        if ("ROLE_FACTORY".equals(role)) {
            Profile profile = service.getProfile();
            model.addAttribute("profile", profile);
            model.addAttribute("role", role);
            model.addAttribute("owner", true);
            return "/profile";
        } else {
            Profile profile = service.getProfile();
            model.addAttribute("profile", profile);
            model.addAttribute("role", role);
            model.addAttribute("owner", true);
            return "/profile";
        }
    }

    @GetMapping("/profile/{id}")
    public String getClientById(Model model, @PathVariable String id) {
        Profile profile = null;
        String role = "ROLE_FACTORY";
        for(GrantedAuthority grantedAuthority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
            role = grantedAuthority.getAuthority();
        }
        profile = service.getProfileById(id);
        if(profile != null) {
            model.addAttribute("profile", profile);
            model.addAttribute("role", role);
            model.addAttribute("owner", false);
            return "/profile";
        } else {
            return "redirect:/profile";
        }
    }
}
