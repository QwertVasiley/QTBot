package com.company.bot;


import com.company.model.DBCityModel;
import com.company.model.DBOldCityModel;
import com.company.repository.GetCityBase;
import com.company.repository.Old_CityRepo;
import com.company.utils.DbConfig;
import com.company.utils.BotConfig;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

import java.util.ArrayList;


public class Main {
    public static void main(String[] args) {

//        ArrayList<DBCityModel> cit = new ArrayList<>(); //для работы с полной базой
//        ArrayList<DBOldCityModel> oldCit = new ArrayList<>(); // для работы с названными городами

        BotConfig.load(); //запуск бота с его конфига
//        DbConfig.getDbConnection();  //запуск конфига базы данных

//        GetCityBase getCityBase = new GetCityBase();
//
//        getCityBase.getCityAllInBase(cit);  //получаю всю базу
//        System.out.println(cit.get(50).getName() + " Это в мейне из общей базы листа");  //пробую вызвать город под индексом 50
//
//        Old_CityRepo old_cityRepo = new Old_CityRepo();
//        old_cityRepo.getOldCity(oldCit);
//        System.out.println(oldCit.get(2).getName() + " Третья запись в таблице названых городов");


        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();

        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }

    }
}
