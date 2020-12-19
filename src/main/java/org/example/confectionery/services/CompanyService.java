package org.example.confectionery.services;

import org.example.confectionery.dataBase.DataBaseCompanies;
import org.example.confectionery.services.entities.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class CompanyService {

    @Autowired
    private DataBaseCompanies dataBaseCompanies;

    public Company getCompanyById(String id) {

        Company company = new Company();
        try {
            company = dataBaseCompanies.getCompanyById(Integer.valueOf(id));
        } catch (SQLException exception) {
            exception.printStackTrace();
        }

        return company;
    }

}
