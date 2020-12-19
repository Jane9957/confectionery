package org.example.confectionery.web.controllers;

import org.example.confectionery.services.CompanyService;
import org.example.confectionery.services.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/company/{id}")
    public String getCompany(Model model, @PathVariable String id) {

        Company company = companyService.getCompanyById(id);
        model.addAttribute("company", company);

        return "/company";
    }
}
