package com.company.repository;

import com.company.utils.DbConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class AddUserInfo {

     public void addUserInfo (Integer user_id_ident, String name, int userScore ) {

        try {

            String command = "INSERT INTO users_game_city (user_id_ident, first_name, game_score) VALUES ( "+ user_id_ident +"  ,  '"+ name + "' ,  + "+ userScore + ")";

            Connection connection = DbConfig.getDbConnection();
            Statement statement = connection.createStatement();
            statement.executeUpdate(command);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}



