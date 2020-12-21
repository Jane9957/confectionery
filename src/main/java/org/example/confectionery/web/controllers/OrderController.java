package org.example.confectionery.web.controllers;

import org.example.confectionery.services.OrderService;
import org.example.confectionery.services.entities.Order;
import org.example.confectionery.services.entities.Profile;
import org.example.confectionery.web.controllers.forms.OrderForm;
import org.example.confectionery.web.controllers.forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/{id}")
    private String getOrder(Model model, @PathVariable String id) {
        String role = "ROLE_CUSTOMER";
        for(GrantedAuthority grantedAuthority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
            role = grantedAuthority.getAuthority();
        }
        if (orderService.checkOwner(id)) {
            Order order = orderService.getOrderByIdSale(id);
            model.addAttribute("order", order);
            return "/order";
        } else {
            return "redirect:/profile";
        }

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

    @GetMapping("/order/{id}/delete")
    public String deleteOrder(Model model, @PathVariable String id) throws SQLException {
        orderService.deleteOrder(id);

        return "redirect:/profile";
    }

    @PostMapping("/showOrders/{id}")
    public String registrationPost(Model model, @ModelAttribute("Order") Order order, @PathVariable String id) throws SQLException {
        order.setIdSale(Integer.valueOf(id));
        orderService.acceptOrder(id);

        return "redirect:/showOrders";
    }

    @GetMapping("/showOrders")
    public String showOrder(Model model, @ModelAttribute("order")Order order) throws SQLException {

        List<Order> orders = new ArrayList<>();
        orders = orderService.showOrders();
        model.addAttribute("orders", orders);

        return "/showOrders";
    }

    @GetMapping("/factoryOrders")
    public String getOrdersOfFactory(Model model) throws SQLException {

        List<Order> orders = new ArrayList<>();
        orders = orderService.getOrdersOfFactory();
        model.addAttribute("orders", orders);

        return "/factoryOrders";
    }
}
