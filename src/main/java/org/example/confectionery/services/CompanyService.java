package org.example.confectionery.services;

import org.example.confectionery.dataBase.DataBaseCompanies;
import org.example.confectionery.dataBase.DataBaseConnect;
import org.example.confectionery.services.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CompanyService {

    @Autowired
    private DataBaseCompanies dataBaseCompanies;

    @Autowired
    private DataBaseConnect dataBaseConnect;

    public Company getCompanyById(String id) {

        Company company = new Company();
        try {
            company = dataBaseCompanies.getCompanyById(Integer.valueOf(id));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return company;
    }

    public Map<Integer, String> getCompanies() {
        Map<Integer, String> map = new HashMap<>();
        try {
            map = dataBaseConnect.getCompanies();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

}
