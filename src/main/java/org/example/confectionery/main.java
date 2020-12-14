package org.example.confectionery;

import ch.qos.logback.core.db.DriverManagerConnectionSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;

public class main {

    @Autowired
    private DataSource src;

    public  void main(String[] args) throws SQLException {


        //Connection connection = src.getConnection();
        //connection.isClosed();


        DBWorker worker = new DBWorker();

        String QUERY = "SELECT * FROM dbo.[user]";
        //Statement statement = worker.getConnection().createStatement();
        //ResultSet resultSet = statement.executeQuery(query);



        PreparedStatement preparedStatement = worker.getConnection().prepareStatement(QUERY);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id_user"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));

            System.out.println(user);
        }

        //String QUERY1 = "{ call getUser()}";
        //CallableStatement callableStatement = worker.getConnection().prepareCall(QUERY1);


    }

    /*public static void main(String[] args) throws SQLException {



        if(!connection.isClosed()) {
            System.out.println("Success");
        }
    }*/
}
