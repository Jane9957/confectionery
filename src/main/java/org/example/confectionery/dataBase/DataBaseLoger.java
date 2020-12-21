package org.example.confectionery.dataBase;

import org.example.confectionery.web.controllers.forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class DataBaseLoger {

    @Autowired
    public DataSource dataSource;

    public String GetDateTime(){
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        return format.format(date);
    }

    public void newLogertext(String text) throws SQLException {
        Connection connection = dataSource.getConnection();

        String NEW_LOGER_TEXT = "{call newLogerText(?)}";

        CallableStatement callableStatement = connection.prepareCall(NEW_LOGER_TEXT);
        callableStatement.setString(1,text);
        callableStatement.execute();
        connection.close();

    }

    /*public List<Report> clientReport (RegistrationForm registrationForm) throws SQLException {
        List<Report> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();

        String CLIENT_REPORT = "{call client_report (?)}";

        CallableStatement callableStatement = connection.prepareCall(CLIENT_REPORT);
        callableStatement.setInt(1,clientform.getId());
        try (ResultSet resultSet = callableStatement.executeQuery()){
            while(resultSet.next()){
                Report report = new Report();
                report.setWaybillid(resultSet.getInt(1));
                report.setWaybillfate(resultSet.getDate(2));

            }
        } catch (Exception e){throw e;}
        connection.close();
        return list;
    }*/
}
