package org.example.confectionery.services;

import org.example.confectionery.dataBase.DataBaseConnect;
import org.example.confectionery.dataBase.DataBaseLoger;
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

    @Autowired
    private DataBaseConnect dataBaseConnect;

    @Autowired
    DataBaseLoger dataBaseLoger;

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
        try {
            dataBaseOrders.createOrder(orderForm, username);
            dataBaseLoger.newLogertext("New order have been added - " + orderForm.getName() + "  At  " + dataBaseLoger.GetDateTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOrder(String id) throws SQLException {
        dataBaseOrders.deleteOrder(Integer.valueOf(id));
        dataBaseLoger.newLogertext("Order " + Integer.valueOf(id) + "  deleted At " + dataBaseLoger.GetDateTime());
    }

    public boolean checkOwner(String id){
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        boolean name = false;
        try {
            name = dataBaseOrders.checkOwner(username, Integer.valueOf(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
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

    public List<Order> showOrders() throws SQLException {
        List<Order> order = new ArrayList<>();
        order = dataBaseConnect.showOrders();

        return order;
    }

    public void acceptOrder(String id) throws SQLException {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        dataBaseOrders.acceptOrder(Integer.valueOf(id), username);
        dataBaseLoger.newLogertext("Order "+ Integer.valueOf(id) + " was accept by " + username + " At " + dataBaseLoger.GetDateTime());
    }

    public List<Order> getOrdersOfFactory() throws SQLException {
        List<Order> order = new ArrayList<>();
        order = dataBaseOrders.getOrdersOfFactory();

        return order;
    }

}
