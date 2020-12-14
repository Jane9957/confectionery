package dataBase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@ComponentScan
@Configuration
public class DataBaseConfig {

    /*private static final String HOST = "jdbc:sqlserver://localhost:1433;databaseName=confectionery_";
    private static final String USERNAME = "user";
    private static final String PASSWORD = "user";
    Connection connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);*/

    @Bean(name = "src")
    public DriverManagerDataSource src() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName("com.microsoft.sqlserver");
        driverManagerDataSource.setUrl("jdbc:sqlserver://localhost:1433;databaseName=confectionery_");
        driverManagerDataSource.setUsername("user");
        driverManagerDataSource.setPassword("user");
        return driverManagerDataSource;
    }
}
