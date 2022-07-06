package com.company.bot;

import com.company.model.DBCityModel;
import com.company.model.DBOldCityModel;
import com.company.model.DBUserStatisticModel;
import com.company.repository.*;
import com.company.utils.DbConfig;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Work {


    ArrayList<DBCityModel> cit = new ArrayList<>(); //для работы с полной базой
    ArrayList<DBOldCityModel> oldCit = new ArrayList<>(); // для работы с названными городами
    ArrayList<DBUserStatisticModel> dBUserStatist = new ArrayList<>(); //для работы с пользователями

    char charLastLeter; //последняя буква названии города
    String cityByUser;
    String myCity;
    int number;
    String name = "";
    Integer score = 0;


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

                return true;
            }
        }
        return false;
    }

    public boolean compareUser(int idUser) {
        dateCollection();
        for (DBUserStatisticModel user : dBUserStatist) {
            int userId = user.getUser_id_ident();
            if (user.getUser_id_ident() == userId)
                return true;
        }
        return false;
    }


    public void recUderInfo(int usId, String usName) {
        int user_id = usId;
        String nameUser = usName;

        AddUserInfo addUserInfo = new AddUserInfo();
        System.out.println(user_id);

        System.out.println(nameUser);
        score = score + 1;
        addUserInfo.addUserInfo(user_id, nameUser, score);
    }


    public boolean compareCityWithBase(String name, ArrayList oldCit, boolean yesNo) throws IOException {
        String city = ""; //создал переменную типа String

        dateCollection();
        int userTelegrammId;
        //сравнение введенного города с базой городов
        for (DBCityModel oCity : cit) {
            city = oCity.getName();
            if (city.equalsIgnoreCase(name)) { //сравнил без учета регистра
                System.out.println("есть такой город в общей базе");
                yesNo = true;
                myCity = name;

                // вызываю метод для записи в список названных городов
                int size = oldCit.size();  //вычисляю размер листа
                AddInOldBase addInOldBase = new AddInOldBase();
                addInOldBase.addOldCity(myCity);
                oldCit.add(size, city);  //добавляю в него правильно названный город





                return yesNo;
            }
        }
        return false;

    }


    public void dateCollection() {    //собираем данные из баз данных
        DbConfig.getDbConnection();  //запуск конфига базы данных
        GetCityBase getCityBase = new GetCityBase();

        getCityBase.getCityAllInBase(cit);  //получаю всю базу
        System.out.println(cit.get(50).getName() + " Это в Bot из общей базы листа");  //пробую вызвать город под индексом 50

        Old_CityRepo old_cityRepo = new Old_CityRepo();
        old_cityRepo.getOldCity(oldCit);

        GetUserInfo getUserInfo = new GetUserInfo();
        getUserInfo.getUserInfo(dBUserStatist);
        // System.out.println(oldCit.get(2).getName() + " Третья запись в таблице названых городов");
    }

}