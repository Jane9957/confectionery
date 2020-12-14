package org.example.confectionery;

import ch.qos.logback.core.db.DriverManagerConnectionSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.*;


public class DBWorker {

    @Autowired
    private DataSource src;

    private static final String HOST = "jdbc:sqlserver://localhost:1433;databaseName=confectionery_";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "user";

    private Connection connection;

    public DBWorker() {
        /*try {

            Connection connection = src.getConnection();
            String QUERY = "SELECT * FROM [user]";
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
            preparedStatement.execute();

            //CallableStatement callableStatement = connection.prepareCall(QUERY);

            //DriverManagerConnectionSource driver = new DriverManagerConnectionSource();
            ////connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
            //Statement statement = connection.createStatement();

            //statement.execute("INSERT INTO confectionery_.dbo.company_type(id_type, name) VALUES (4, 'loool')");

            //int res = statement.executeUpdate("UPDATE confectionery_.dbo.company_type SET name='murr' WHERE id_type=4");
            //System.out.println(res);

            //ResultSet res = statement.executeQuery("SELECT * FROM confectionery_.dbo.company_type");

            /*statement.addBatch("INSERT INTO confectionery_.dbo.company_type(id_type, name) VALUES (06, 'loool1')");
            statement.addBatch("INSERT INTO confectionery_.dbo.company_type(id_type, name) VALUES (07, 'loool2')");
            statement.addBatch("INSERT INTO confectionery_.dbo.company_type(id_type, name) VALUES (08, 'loool3')");

            statement.executeBatch();

            statement.clearBatch(); //очистка/////

            boolean result = statement.isClosed();
            System.out.println(result);

            statement.getConnection();

            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }*/
    }

    public Connection getConnection() {
        return connection;
    }
}
