package org.example.confectionery.web.controllers;

import org.example.confectionery.services.OrderService;
import org.example.confectionery.services.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

}
