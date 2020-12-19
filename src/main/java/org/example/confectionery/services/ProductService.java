package org.example.confectionery.services;

import org.example.confectionery.dataBase.DataBaseConnect;
import org.example.confectionery.dataBase.DataBaseProducts;
import org.example.confectionery.services.entities.Product;
import org.example.confectionery.services.entities.Raw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private DataBaseProducts dataBaseProducts;

    public Product getRecipeOfProduct(String id) {
        Product product = new Product();

        try {
            product = dataBaseProducts.getRecipeOfProduct(Integer.valueOf(id));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return product;
    }

}
