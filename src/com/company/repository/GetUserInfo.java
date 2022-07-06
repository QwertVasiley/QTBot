package com.company.repository;

import com.company.model.DBCityModel;
import com.company.model.DBUserStatisticModel;
import com.company.utils.DbConfig;

import java.sql.*;
import java.util.ArrayList;



public class GetUserInfo {

    ArrayList<DBUserStatisticModel> userStatistic;

    public ArrayList getUserInfo(ArrayList<DBUserStatisticModel> userStatistic) {

        try {
            Connection connection = DbConfig.getDbConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT * FROM users_game_city");

            while (resultSet.next()) {
                DBUserStatisticModel dbUserStatisticModel = new DBUserStatisticModel();
                dbUserStatisticModel.setUser_id_ident(resultSet.getInt("user_id_ident"));
                dbUserStatisticModel.setUserName(resultSet.getString("first_name"));
                dbUserStatisticModel.setScore(resultSet.getLong("game_score"));
                userStatistic.add(dbUserStatisticModel);

            }

        } catch (SQLException e) {
            System.out.println("GetUserInfo не сработал");
            e.printStackTrace();
        }
        return userStatistic;
    }
}
