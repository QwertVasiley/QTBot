package com.company.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class BotConfig {
    public static final String CONFIGURATION_BOT_FILE = "./config/bot.properties";

    public static String BOT_NAME;
    public static String BOT_TOKEN;

      public static void load (){


        Properties botSettings = new Properties();

        try {
            InputStream is = new FileInputStream(new File(CONFIGURATION_BOT_FILE));
            botSettings.load(is);
            is.close();
            System.out.println("Конфиг бота загружен");

        } catch (Exception e) {
            System.out.println("Конфиг бота не загружен, смотри пути к конфигу");
        }


        BOT_NAME = botSettings.getProperty("BotName");
        BOT_TOKEN = botSettings.getProperty("BotToken");


    }
}
