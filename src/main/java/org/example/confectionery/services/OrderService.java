package org.example.confectionery.services;

import org.example.confectionery.dataBase.DataBaseOrders;
import org.example.confectionery.services.entities.Order;
import org.example.confectionery.web.controllers.forms.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private DataBaseOrders dataBaseOrders;

    public Order getOrderByIdSale(String id) {
        Order order = new Order();
        try {
            order = dataBaseOrders.getOrderByIdSale(Integer.valueOf(id));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return order;
    }

    public void createOrder(OrderForm orderForm) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        //orderForm.setDate(date);
        try {
            dataBaseOrders.createOrder(orderForm, username);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<OrderForm> getProducts() {
        List<OrderForm> orderForms = new ArrayList<>();
        try {
            orderForms = dataBaseOrders.getProduct();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return orderForms;
    }

}
