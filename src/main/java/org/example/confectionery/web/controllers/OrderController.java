package org.example.confectionery.web.controllers;

import org.example.confectionery.services.OrderService;
import org.example.confectionery.services.entities.Order;
import org.example.confectionery.web.controllers.forms.OrderForm;
import org.example.confectionery.web.controllers.forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/{id}")
    private String getOrder(Model model, @PathVariable String id) {
        Order order = orderService.getOrderByIdSale(id);
        model.addAttribute("order", order);

        return "/order";
    }

    @PostMapping("/createOrder/{id}")
    public String registrationPost(Model model, @ModelAttribute("orderForm") OrderForm orderForm, @PathVariable String id) {
        orderForm.setIdProd(Integer.valueOf(id));
        orderService.createOrder(orderForm);

        return "redirect:/profile";
    }

    @GetMapping("/createOrder")
    public String addOrder(Model model, @ModelAttribute("orderForm")OrderForm orderForm) {

        List<OrderForm> orderForms = new ArrayList<>();
        orderForms = orderService.getProducts();
        model.addAttribute("products", orderForms);

        return "/createOrder";
    }

}
