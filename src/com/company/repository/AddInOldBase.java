package com.company.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.company.model.DBOldCityModel;
import com.company.utils.DbConfig;

import java.sql.*;

public class AddInOldBase {

    public void addOldCity(String myCity) {

        try {

            String cit = myCity;

            String command = "INSERT INTO old_city (name) VALUES ('" + cit + "')";
            Connection connection = DbConfig.getDbConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(command);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}




