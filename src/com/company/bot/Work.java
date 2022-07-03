package com.company.bot;

import com.company.model.DBCityModel;
import com.company.model.DBOldCityModel;
import com.company.repository.GetCityBase;
import com.company.repository.Old_CityRepo;
import com.company.utils.BotConfig;
import com.company.utils.DbConfig;
import com.google.inject.internal.asm.$AnnotationVisitor;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Random;

public class Work {

    ArrayList<DBCityModel> cit = new ArrayList<>(); //для работы с полной базой
    ArrayList<DBOldCityModel> oldCit = new ArrayList<>(); // для работы с названными городами
    char charLastLeter; //последняя буква названии города
    String cityByUser;
    String myCity;
    int number;
    String name = "";

    public int amount() {  //получаю общее кол-во городов
        this.number = number;
        dateCollection();
        number = cit.size() - 1;      // сколько записей - столько и городов в базе
        return number;
    }

    public String rand() { //рандомный город
        Random rnd = new Random();
        number = cit.size() - 1;      // сколько записей - столько и городов в базе
        int rndNum = rnd.nextInt(number); // генерю случайное число
        return cit.get(rndNum).getName().toUpperCase();
    }

    public char findBeadLastLiter(String myCity) {  //проверка последней буквы

        int indexLastGoodLiter = 1; //для индекса последней буквы завел переменную
        int last = myCity.length() - indexLastGoodLiter;  //нахожу последний символ в названии города
        charLastLeter = myCity.charAt(last); //присваиваю его charLastLeter
        //ПРОВЕРКА НА ПОСЛЕДНЮЮ БУКВУ, КОТОРАЯ ВНЕ ИГРЫ "Ы" "Ъ" "Ё" "Й" "Ь"
        boolean goodLiter = false;
        while (goodLiter == false) {
            if (charLastLeter == 'Ы' || charLastLeter == 'Ъ' || charLastLeter == 'Ё' || charLastLeter == 'Й' || charLastLeter == 'Ь') {
                indexLastGoodLiter++;
                last = myCity.length() - indexLastGoodLiter;
                charLastLeter = myCity.charAt(last);
                goodLiter = false;
            } else {
                goodLiter = true;
            }
        }
        return charLastLeter;
    }

    public boolean compareOld(String cityByUser) {
        dateCollection();
        for (DBOldCityModel old : oldCit) {
            String oldCity = old.getName();  // получаю названия из старых городов

            if (oldCity.equalsIgnoreCase(cityByUser)) {

                return false;
            }
        }
        return true;
    }


    public void dateCollection() {    //собираем данные из баз данных
        DbConfig.getDbConnection();  //запуск конфига базы данных
        GetCityBase getCityBase = new GetCityBase();

        getCityBase.getCityAllInBase(cit);  //получаю всю базу
        System.out.println(cit.get(50).getName() + " Это в Bot из общей базы листа");  //пробую вызвать город под индексом 50

        Old_CityRepo old_cityRepo = new Old_CityRepo();
        old_cityRepo.getOldCity(oldCit);
        System.out.println(oldCit.get(2).getName() + " Третья запись в таблице названых городов");
    }


}