package org.example.confectionery.dataBase;

import org.example.confectionery.services.entities.Order;
import org.example.confectionery.services.entities.Raw;
import org.example.confectionery.web.controllers.forms.OrderForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

@Service
public class DataBaseOrders {

    @Autowired
    private DataSource src;

    public List<OrderForm> getProduct() throws SQLException {
        List<OrderForm> list = new ArrayList<>();
        Connection connection = src.getConnection();

        String GET_PRODUCT_FOR_ORDER = "{call getProduct() }";

        CallableStatement callableStatement = connection.prepareCall(GET_PRODUCT_FOR_ORDER);

        try (ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()) {
                OrderForm orderForm = new OrderForm();
                orderForm.setIdProd(resultSet.getInt(1));
                orderForm.setName(resultSet.getString(2));
                orderForm.setPrice(resultSet.getInt(3));
                orderForm.setWeight(resultSet.getInt(4));
                list.add(orderForm);
            }
        }
        callableStatement.execute();
        connection.close();

        return list;
    }

    public List<Order> getOrdersOfFactory() throws SQLException {
        List<Order> result = new ArrayList<>();
        Connection connection = src.getConnection();
        String GET_ORDERS_OF_FACTORY = "{ call getRaw() }";
        CallableStatement callableStatement = connection.prepareCall(GET_ORDERS_OF_FACTORY);

        try (ResultSet resultSet = callableStatement.executeQuery()) {
            while(resultSet.next()) {
                Order order = new Order();
                order.setIdRaw(resultSet.getInt(1));
                order.setRaws(getOrderRaws(order.getIdRaw()));
                result.add(order);
            }
        } catch (Exception e) {
            throw e;
        }
        connection.close();
        return result;
    }

    public List<Raw> getOrderRaws(Integer id) throws SQLException{
        List<Raw> result = new ArrayList<>();
        Connection connection = src.getConnection();
        String GET_ORDERS_OF_FACTORY = "{ call getOrdersOfFactory(?) }";
        CallableStatement callableStatement = connection.prepareCall(GET_ORDERS_OF_FACTORY);
        callableStatement.setInt("id_raw", id);
        try (ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()) {
                Raw raw = new Raw();
                raw.setNameRaw(resultSet.getString(1));
                raw.setQtRaw(resultSet.getInt(2));
                raw.setPriceRaw(resultSet.getInt(3));
                result.add(raw);
            }
        } catch (Exception e) {
            throw e;
        }

        connection.close();
        return result;
    }

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
                order.setQtProduct(resultSet.getInt(7));
                order.setFirstName(resultSet.getString(8));
                order.setMiddleName(resultSet.getString(9));
                order.setLastName(resultSet.getString(10));
                order.setEmail(resultSet.getString(11));
                order.setPhone(resultSet.getString(12));
                order.setCompany(resultSet.getString(13));
                order.setPriceTotal(resultSet.getInt(14));
                order.setIdProd(resultSet.getInt(15));
            }
        }
        callableStatement.execute();
        connection.close();

        return order;
    }

    public boolean checkOwner(String username, Integer id) throws SQLException {
        Connection connection = src.getConnection();

        String CHECK_OWNER = "{ call checkOwner(?, ?) }";

        CallableStatement callableStatement = connection.prepareCall(CHECK_OWNER);
        callableStatement.setString("username", username);
        callableStatement.setInt("id_sale", id);

        ResultSet resultSet = callableStatement.executeQuery();
        return resultSet.next();

    }

    public void deleteOrder(Integer id) throws SQLException {
        Connection connection = src.getConnection();
        String CANCEL_ORDER = "{call cancelOrder(?) }";
        CallableStatement callableStatement = connection.prepareCall(CANCEL_ORDER);
        callableStatement.setInt("id_sale", id);
        callableStatement.execute();
        connection.close();
    }

    public void createOrder(OrderForm orderForm, String username) throws SQLException {
        Connection connection = src.getConnection();
        String CREATE_ORDER_BY_USER = "{call createOrderByUser(?, ?, ?) }";
        CallableStatement callableStatement = connection.prepareCall(CREATE_ORDER_BY_USER);
        callableStatement.setString("Login", username);
        callableStatement.setInt("id_prod", orderForm.getIdProd());
        callableStatement.setInt("qt_prod", orderForm.getQt());

        callableStatement.execute();
        connection.close();
    }

    public void acceptOrder(Integer id, String username) throws SQLException {
        Connection connection = src.getConnection();
        String ACCEPT_ORDER = "{call acceptOrder(?) }";
        CallableStatement callableStatement = connection.prepareCall(ACCEPT_ORDER);
        callableStatement.setInt("id_sale", id);

        try (ResultSet resultSet = callableStatement.executeQuery()){
            while (resultSet.next()) {
                String CREATE_ORDER_FOR_FACTORY = "{call createOrderForFactory(?, ?, ?) }";
                CallableStatement callableStatement1 = connection.prepareCall(CREATE_ORDER_FOR_FACTORY);
                callableStatement1.setString("Login", username);
                callableStatement1.setInt("id_raw", resultSet.getInt(1));
                callableStatement1.setInt("qt_raw", resultSet.getInt(2));
                callableStatement1.execute();
            }

        } catch (Exception e) {
            throw e;
        }
        callableStatement.executeQuery();
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
