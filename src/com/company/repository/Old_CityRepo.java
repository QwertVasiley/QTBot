package com.company.repository;

import com.company.model.DBCityModel;
import com.company.model.DBOldCityModel;
import com.company.utils.DbConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Old_CityRepo {


    ArrayList<DBOldCityModel> oldCityModel;


    public ArrayList getOldCity(ArrayList<DBOldCityModel> oldCit) {
        try {
            Connection connection = DbConfig.getDbConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet;

            resultSet = statement.executeQuery("SELECT * FROM old_city");
            while (resultSet.next()) {
                DBOldCityModel dbOldCityModel = new DBOldCityModel();
                dbOldCityModel.setId(resultSet.getLong("id"));
                dbOldCityModel.setName(resultSet.getString("name"));

                oldCit.add(dbOldCityModel);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return oldCit;
    }
}





