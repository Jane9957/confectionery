package org.example.confectionery.dataBase;

import org.example.confectionery.services.entities.Product;
import org.example.confectionery.services.entities.Raw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataBaseProducts {

    @Autowired
    private DataSource src;

    public Product getRecipeOfProduct(Integer id) throws SQLException {
        List<Raw> result = new ArrayList<>();
        Connection connection = src.getConnection();

        String GET_RECIPE_OF_PRODUCT = "{ call getRecipeByIdProduction(?) }";

        CallableStatement callableStatement = connection.prepareCall(GET_RECIPE_OF_PRODUCT);
        callableStatement.setInt("id_prod", id);
        Product product = new Product();

        try (ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()) {
                Raw raw = new Raw();
                product.setNameProduct(resultSet.getString(1));
                raw.setNameRaw(resultSet.getString(2));
                raw.setQtRaw(resultSet.getInt(3));
                result.add(raw);
            }
        } catch (Exception e) {
            throw e;
        }
        connection.close();
        product.setRaws(result);
        return product;

    }

}
