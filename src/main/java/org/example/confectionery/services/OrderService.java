package org.example.confectionery.services;

import org.example.confectionery.dataBase.DataBaseOrders;
import org.example.confectionery.services.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

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

}
