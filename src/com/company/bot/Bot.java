package com.company.bot;

import com.company.model.DBCityModel;
import com.company.model.DBOldCityModel;
import com.company.repository.AddUserInfo;
import com.company.utils.BotConfig;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


import java.io.IOException;
import java.util.ArrayList;


public class Bot extends TelegramLongPollingBot {

    ArrayList<DBCityModel> cit = new ArrayList<>(); //для работы с полной базой
    ArrayList<DBOldCityModel> oldCit = new ArrayList<>(); // для работы с названными городами
    char charLastLeter; //последняя буква названии города
    int numOfCites; //общее кол-во городов в базе
    String rndCity; // рандомный город
    String cityByUser;
    String myCity;

    char simbol;
    String name = "";
    boolean cicle = true;

    public Integer userIdIdent;  //id пользователя в telegram
    public String userName; //Имя пользователя
    public int score; //кол-во правильно названных городов

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "/help":
                    sendMsg(message, "Привет, я бот игры 'Города' \n для старта игры используй /start \n лучшая пятёрка игроков /top5 \n лучшая десятка игроков /top10 \n информация о твоей игре /info");
                    break;
                case "/top5":
                    sendMsg(message, "Тут вывелась пятерка лучших игроков");
                    break;
                case "/top10":
                    sendMsg(message, "Тут вывелась десятка лучших игроков");
                    break;
                case "/info":
                    sendMsg(message, "Тут вывелась инфо о твоей игре и количество набраных очков");
                case "/start":
                case "/старт":
                    System.out.println("Start game");
                    gameStart();
                    sendMsg(message, "В базе  " + numOfCites + " городов ");
                    sendMsg(message, "Старт игры, я начну первым и мой город:    " + rndCity);
                    sendMsg(message, "Вспоминаем город на букву  " + Character.toUpperCase(simbol));
                    return;
            }
            if (message.hasText()) {
                cityByUser = message.getText();
                if (message.hasText()) {
                    userIdIdent = message.getFrom().getId();
                    userName = message.getFrom().getFirstName();
                    score = 1;

                    compareOld();
                    System.out.println(cityByUser);
                    while (cicle == true) {
                        sendMsg(message, "Такой город уже называли \n продолжаем вспомниать город на букву   " + Character.toUpperCase(simbol));
                        break;
                    }
                    compareBase();  //проверка по общей базе
                    System.out.println("Good: выполнился метод сравнения по общей базе");


                }
            }
        }
    }


    public void gameStart() {
        Work amountCity = new Work();
        numOfCites = amountCity.amount();  //получаю кол-во городов
        rndCity = amountCity.rand();  // получаю рандомный город
        Work work2 = new Work();
        simbol = work2.findBeadLastLiter(rndCity);
    }

    public void compareOld() {

        Work compar = new Work();
        cicle = compar.compareOld(cityByUser);

    }


    public void compareBase() {
        System.out.println("ТУт проверка по базе всех городов");
        Work compareAllBase = new Work();
        try {
            compareAllBase.compareCityWithBase(cityByUser, oldCit, userIdIdent, userName);
        } catch (IOException e) {
            System.out.println("Не выполнился метод compareCityWithBase в классе Work");
            e.printStackTrace();
        }


    }


    private void sendMsg(Message message, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        //sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(s);

        try {
            sendMessage(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return BotConfig.BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BotConfig.BOT_TOKEN;
    }


}