package com.company.repository;

import com.company.model.DBCityModel;
import com.company.model.DBUserStatisticModel;
import com.company.utils.DbConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class GetUserInfo {

    public void getUserInfo() {


        try {
            Connection connection = DbConfig.getDbConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT * from users_game_city");
            while (resultSet.next()) {
                DBUserStatisticModel dbUserStatisticModel = new DBUserStatisticModel();
                dbUserStatisticModel.setUser_id_ident((int) resultSet.getLong("user_id_ident"));
                dbUserStatisticModel.setUserName(resultSet.getString("first_name"));
                dbUserStatisticModel.setScore(resultSet.getLong("game_score"));

            }

            // System.out.println(allCityTable.get(48).getName()); //для теста вывел город по индексу

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }
}
