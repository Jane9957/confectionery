package org.example.confectionery.dataBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;

@Service
public class DataBaseValidation {

    @Autowired
    private DataSource dataSource;

    public boolean checkLogin(String login) throws SQLException {
        Connection connection = dataSource.getConnection();
        String CHECK_LOGIN = "{ call checkLogin(?) }";
        CallableStatement callableStatement = connection.prepareCall(CHECK_LOGIN);
        callableStatement.setString(1, login);

        ResultSet resultSet = callableStatement.executeQuery();
        return resultSet.next();
    }
}
