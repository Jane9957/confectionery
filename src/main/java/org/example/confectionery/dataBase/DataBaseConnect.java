package org.example.confectionery.dataBase;

import org.example.confectionery.web.controllers.forms.RegistrationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.example.confectionery.services.entities.Profile;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class DataBaseConnect {

    @Autowired
    private DataSource src;

    public static void createUser(RegistrationForm form) throws SQLException {
        Connection connection = src.getConnection();

        String REGISTRATION_QUERY = "{call toRegistr(?, ?, ?, ?, ?, ?, ?, ?, ?) }";

        CallableStatement callableStatement = connection.prepareCall(REGISTRATION_QUERY);
        callableStatement.setString("login", form.getLogin());
        callableStatement.setString("password", form.getPassword());
        callableStatement.setString("email", form.getEmail());
        //callableStatement.setLong(9, Long.valueOf(form.getPhone()));
        callableStatement.setString("phone", form.getPhone());
        callableStatement.setString("first_name", form.getName_first());
        callableStatement.setString("middle_name", form.getName_middle());
        callableStatement.setString("last_name", form.getName_last());
        callableStatement.setString("birthday", form.getBirthday());
        callableStatement.setInt("id_company", form.getCompany());

        callableStatement.execute();
        connection.close();
    }

    public Profile getProfile() throws SQLException {
        Connection connection = src.getConnection();

        //дописать по данному типу

        //String QUERY = "SELECT * FROM [user]";
        String QUERY = "{ call dbo.getUser()}";
        CallableStatement callableStatement = connection.prepareCall(QUERY);
        ResultSet resultSet = callableStatement.executeQuery();

        Profile profile = new Profile();

        if(resultSet.next()) {
            profile.setId(resultSet.getInt("id_user"));
            profile.setLogin(resultSet.getString("login"));
            profile.setPassword(resultSet.getString("password"));
            profile.setName_first(resultSet.getString("first_name"));
            profile.setName_middle(resultSet.getString("middle_name"));
            profile.setName_last(resultSet.getString("last_name"));
            profile.setEmail(resultSet.getString("email"));
            profile.setPhone(resultSet.getString("phone"));
            profile.setBirthday(resultSet.getString("birthday"));

            //добавить название компании

            System.out.println(profile);

        }
        connection.close();
        return profile;

    }



}
