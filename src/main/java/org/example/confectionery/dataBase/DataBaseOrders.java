package org.example.confectionery.dataBase;

import org.example.confectionery.services.entities.Order;
import org.example.confectionery.web.controllers.forms.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class DataBaseOrders {

    @Autowired
    private DataSource src;

    public Order getOrderByIdSale(Integer id) throws SQLException {
        Connection connection = src.getConnection();
        String GET_ORDER_BY_ID_SALE = "{call getOrderByIdSale(?) }";
        CallableStatement callableStatement = connection.prepareCall(GET_ORDER_BY_ID_SALE);
        callableStatement.setInt("id_sale", id);
        Order order = new Order();

        try (ResultSet resultSet = callableStatement.executeQuery()){
            if (resultSet.next()) {
                order.setIdSale(resultSet.getInt(1));
                order.setDate(resultSet.getString(2));
                order.setStatus(resultSet.getString(3));
                order.setNameProduct(resultSet.getString(4));
                order.setPriceProduct(resultSet.getInt(5));
                order.setWeightProduct(resultSet.getInt(6));
                order.setFirstName(resultSet.getString(7));
                order.setMiddleName(resultSet.getString(8));
                order.setLastName(resultSet.getString(9));
                order.setEmail(resultSet.getString(10));
                order.setPhone(resultSet.getString(11));
                order.setCompany(resultSet.getString(12));
            }
        }
        callableStatement.execute();
        connection.close();

        return order;
    }

    public void cancelOrder(Integer id) throws SQLException {
        Connection connection = src.getConnection();
        String CANCEL_ORDER = "{call cancelOrder(?) }";
        CallableStatement callableStatement = connection.prepareCall(CANCEL_ORDER);
        callableStatement.setInt("id_sale", id);
        callableStatement.execute();
        connection.close();
    }

    public void createOrder(OrderForm orderForm, String username) throws SQLException {
        Connection connection = src.getConnection();
        String CREATE_ORDER_BY_USER = "{call createOrderByUser(?, ?) }";
        CallableStatement callableStatement = connection.prepareCall(CREATE_ORDER_BY_USER);
        callableStatement.setString("Login", username);
        callableStatement.setInt("id_prod", orderForm.getIdProd());

        callableStatement.execute();
        connection.close();
    }

    public Map<Integer, String> getProduction() throws SQLException {
        Map<Integer, String> map = new HashMap<>();
        Connection connection = src.getConnection();
        String GET_PRODUCTION = "{ call getProduction() }";
        CallableStatement callableStatement = connection.prepareCall(GET_PRODUCTION);
        try (ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()) {
                map.put(resultSet.getInt("id_prod"),
                        resultSet.getString("name"));
            }
        } catch (Exception e) {
            throw e;
        }
        return map;
    }

    public Map<Integer, String> getRaw() throws SQLException {
        Map<Integer, String> map = new HashMap<>();
        Connection connection = src.getConnection();
        String GET_RAW = "{ call getRaw() }";
        CallableStatement callableStatement = connection.prepareCall(GET_RAW);
        try (ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()) {
                map.put(resultSet.getInt("id_raw"),
                        resultSet.getString("name"));
            }
        } catch (Exception e) {
            throw e;
        }
        return map;
    }
}
