package com.company.repository;
import com.company.model.DBCityModel;
import com.company.utils.DbConfig;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class GetCityBase {

     ArrayList<DBCityModel> allCityTable;

    public ArrayList getCityAllInBase(ArrayList<DBCityModel> allCityTable) {


        try {
            Connection connection = DbConfig.getDbConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT * from base_city");
            while (resultSet.next()) {
                DBCityModel dbCityModel = new DBCityModel();
                dbCityModel.setCountry_id(resultSet.getLong("country_id"));
                dbCityModel.setRegion_id(resultSet.getLong("region_id"));
                dbCityModel.setName(resultSet.getString("name"));
                dbCityModel.setId_city(resultSet.getLong("id_city"));

                allCityTable.add(dbCityModel);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCityTable;
    }
}
