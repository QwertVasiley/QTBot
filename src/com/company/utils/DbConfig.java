package com.company.utils;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DbConfig {

    public static final String CONFIGURATION_DB_FILE = "./config/database.properties";

    public static String DB_DRIVER;
    public static String DB_URL;
    public static String DB_USER;
    public static String DB_PWD;


    public static Connection getDbConnection() {



        Properties databaseSettings = new Properties();

        try {
            InputStream is = new FileInputStream(new File(CONFIGURATION_DB_FILE));
            databaseSettings.load(is);
            is.close();
            System.out.println("Конфиг Database загружен успешно!");
            System.out.println();
        } catch (Exception e) {

            System.out.println("Конфиг базы данных не загружен");
        }

        DB_DRIVER = databaseSettings.getProperty("DbDriver");
        DB_URL = databaseSettings.getProperty("DbUrl");
        DB_USER = databaseSettings.getProperty("DbUser");
        DB_PWD = databaseSettings.getProperty("DbPwd");


        try {
            Class.forName(DB_DRIVER);
            System.out.println("Драйвер JDBC загружен ");

        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось зарегестрировать драйвер");
            e.printStackTrace();
        }

        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
            System.out.println("соединение с БД OK!");

        } catch (SQLException e) {
            System.out.println("Не удалось получить connection");
            e.printStackTrace();
        }
        return connection;
    }
}