package org.example.confectionery.dataBase;

import org.example.confectionery.services.entities.Company;
import org.example.confectionery.services.entities.Member;
import org.example.confectionery.services.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataBaseCompanies {

    @Autowired
    private DataSource dataSource;

    public Company getCompanyById(Integer id) throws SQLException {

        List<Member> result = new ArrayList<>();
        Connection connection = dataSource.getConnection();

        String GET_COMPANY_BY_ID = "{ call getCompanyById(?) }";

        CallableStatement callableStatement = connection.prepareCall(GET_COMPANY_BY_ID);
        callableStatement.setInt("id_company", id);
        Company company = new Company();

        try (ResultSet resultSet = callableStatement.executeQuery()) {
            while (resultSet.next()) {
                company.setName(resultSet.getString(1));
                company.setAddress(resultSet.getString(2));
                company.setWeb(resultSet.getString(3));
                company.setPhone(resultSet.getString(4));

                Member member = new Member();
                member.setFirstName(resultSet.getString(5));
                member.setMiddleName(resultSet.getString(6));
                member.setLastName(resultSet.getString(7));
                member.setEmail(resultSet.getString(8));
                member.setPhone(resultSet.getString(9));
                member.setBirthday(resultSet.getString(10));
                result.add(member);
            }
        } catch (Exception e) {
            throw e;
        }
        connection.close();
        company.setMembers(result);

        return company;
    }
}
