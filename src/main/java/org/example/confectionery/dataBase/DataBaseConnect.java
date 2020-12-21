package org.example.confectionery.dataBase;

import org.example.confectionery.services.entities.*;
import org.example.confectionery.web.controllers.forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataBaseConnect {

    @Autowired
    private DataSource src;

    public void createUser(RegistrationForm form) throws SQLException {
        Connection connection = src.getConnection();

        String REGISTRATION_QUERY = "{call toRegistr(?, ?, ?, ?, ?, ?, ?, ?, ?) }";

        CallableStatement callableStatement = connection.prepareCall(REGISTRATION_QUERY);
        callableStatement.setString("login", form.getLogin());
        callableStatement.setString("password", form.getPassword());
        callableStatement.setString("email", form.getEmail());
        //callableStatement.setLong(9, Long.valueOf(form.getPhone()));
        callableStatement.setString("phone", form.getPhone());
        callableStatement.setString("FirstName", form.getName_first());
        callableStatement.setString("MiddleName", form.getName_middle());
        callableStatement.setString("LastName", form.getName_last());
        callableStatement.setString("birthday", form.getBirthday());
        callableStatement.setInt("Company", form.getCompany());

        callableStatement.execute();
        connection.close();
    }

    public Map<Integer, String> getCompanies() throws SQLException {
        Map<Integer, String> map = new HashMap<>();
        Connection connection = src.getConnection();

        String GET_COMPANIES = "{call getCompanies() }";

        CallableStatement callableStatement = connection.prepareCall(GET_COMPANIES);
        try (ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()) {
                map.put(resultSet.getInt(1), resultSet.getString(2));
            }
        } catch (Exception e) {
            throw e;
        }

        return map;
    }

    private List<CustomOrder> getOrdersByClientId(Integer id) throws SQLException {
        List<CustomOrder> list = new ArrayList<>();
        Connection connection = src.getConnection();
        String GET_OFFERS_BY_ID = "{ call getOffersById(?) }";
        CallableStatement callableStatement = connection.prepareCall(GET_OFFERS_BY_ID);
        callableStatement.setInt("id_user", id);
        try (ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()) {
                CustomOrder customOrder = new CustomOrder();
                customOrder.setId(resultSet.getInt(1));
                customOrder.setName(resultSet.getString(2));
                customOrder.setPrice(resultSet.getInt(3));
                customOrder.setDate(resultSet.getString(4));
                customOrder.setStatus(resultSet.getString(5));
                customOrder.setQtProduct(resultSet.getInt(6));
                if ("Entered".equals(customOrder.getStatus())) {
                    customOrder.setCancel(true);
                } else {
                    customOrder.setCancel(false);
                }
                list.add(customOrder);
            }
        } catch (Exception e) {
            throw e;
        }
        connection.close();
        return list;
    }

    public Profile getProfileById(Integer id) throws SQLException {
        Connection connection = src.getConnection();
        //String QUERY = "{ call dbo.getUser()}";
        String GET_CLIENT_PROFILE = "{ call getProfileById(?) }";
        CallableStatement callableStatement = connection.prepareCall(GET_CLIENT_PROFILE);
        callableStatement.setInt("id", id);
        Profile profile = null;

        try (ResultSet resultSet = callableStatement.executeQuery()) {
            if(resultSet.next()) {
                profile = new Profile();
                profile.setLogin(resultSet.getString("login"));
                profile.setName_first(resultSet.getString("first_name"));
                profile.setName_middle(resultSet.getString("middle_name"));
                profile.setName_last(resultSet.getString("last_name"));
                profile.setEmail(resultSet.getString("email"));
                profile.setPhone(resultSet.getString("phone"));
                profile.setBirthday(resultSet.getString("birthday"));
                profile.setCompany(resultSet.getString(8));

                //добавить название компании

                System.out.println(profile);
            }
        } catch (Exception e) {
            throw e;
        }
        connection.close();
        return profile;

    }

    public Profile getProfileByUsername(String username) throws SQLException {
        Connection connection = src.getConnection();
        String GET_CLIENT_PROFILE_BY_ID = "{ call getProfileByUsername(?) }";
        CallableStatement callableStatement = connection.prepareCall(GET_CLIENT_PROFILE_BY_ID);
        callableStatement.setString("login", username);
        Profile profile = new Profile();
        try (ResultSet resultSet = callableStatement.executeQuery()) {
            if (resultSet.next()) {
                profile.setId(resultSet.getInt("id_user"));
                profile.setName_first(resultSet.getString("first_name"));
                profile.setName_middle(resultSet.getString("middle_name"));
                profile.setName_last(resultSet.getString("last_name"));
                profile.setEmail(resultSet.getString("email"));
                profile.setPhone(resultSet.getString("phone"));
                profile.setBirthday(resultSet.getString("birthday"));
                profile.setCompany(resultSet.getString(8));

                profile.setOrders(getOrderByIdUser(profile.getId()));

            }
        } catch (Exception e) {
            throw e;
        }
        connection.close();
        return profile;
    }

    public List<Order> getOrderByIdUser(Integer id) throws SQLException {

        List<Order> result = new ArrayList<>();
        Connection connection = src.getConnection();

        String GET_ORDERS_BY_ID_USER = "{ call getOrderByIdUser(?) }"; //to create

        CallableStatement callableStatement = connection.prepareCall(GET_ORDERS_BY_ID_USER);
        callableStatement.setInt(1, id);

        try (ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()) {
                Order order = new Order();
                order.setIdSale(resultSet.getInt(1));
                order.setDate(resultSet.getString(2));
                order.setStatus(resultSet.getString(3));
                order.setNameProduct(resultSet.getString(4));
                order.setPriceProduct(resultSet.getInt(5));
                order.setWeightProduct(resultSet.getInt(6));
                order.setQtProduct(resultSet.getInt(7));
                order.setPriceTotal(resultSet.getInt(8));

                result.add(order);
            }
        } catch (Exception e) {
            throw e;
        }
        connection.close();

        return result;
    }

    public List<Order> showOrders() throws SQLException {

        List<Order> result = new ArrayList<>();
        Connection connection = src.getConnection();

        String SHOW_ORDERS = "{ call showOrders() }";

        CallableStatement callableStatement = connection.prepareCall(SHOW_ORDERS);
        try(ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()) {
                Order order = new Order();
                order.setIdSale(resultSet.getInt(1));
                order.setDate(resultSet.getString(2));
                order.setStatus(resultSet.getString(3));
                order.setNameProduct(resultSet.getString(4));
                order.setPriceProduct(resultSet.getInt(5));
                order.setWeightProduct(resultSet.getInt(6));
                order.setQtProduct(resultSet.getInt(7));
                order.setPriceTotal(resultSet.getInt(8));

                result.add(order);
            }
        } catch (Exception e) {
            throw e;
        }
        return result;
    }
}
