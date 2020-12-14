package dataBase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.entities.Profile;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class DataBaseConnect {

    @Autowired
    private DataSource src;

    public Profile main() throws SQLException {
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
            System.out.println(profile);

        }

        return profile;

    }


}
