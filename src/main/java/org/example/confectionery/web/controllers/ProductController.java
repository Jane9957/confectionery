package org.example.confectionery.web.controllers;

import org.example.confectionery.services.ProductService;
import org.example.confectionery.services.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public String getProduct(Model model, @PathVariable String id) {
        Product product = productService.getRecipeOfProduct(id);
        model.addAttribute("product", product);


        return "/product";
    }

}

