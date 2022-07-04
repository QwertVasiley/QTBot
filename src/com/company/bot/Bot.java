package com.company.bot;

import com.company.model.DBCityModel;
import com.company.model.DBOldCityModel;
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
                    compareOld();
                    System.out.println(cityByUser);
                    while (cicle == true) {
                        sendMsg(message, "Такой город уже называли \n продолжаем вспомниать город на букву   " + Character.toUpperCase(simbol));
                        break;
                    }
                    compareBase();  //проверка по общей базе
                    System.out.println("выполнился метод сравнения по общей базе");

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
            compareAllBase.compareCityWithBase(cityByUser, oldCit);
        } catch (IOException e) {
            System.out.println("Не выполнился метод compareCityWithBase в классе Work");
            e.printStackTrace();
        }


    }



//
//                if (message.hasText() && cicle == false) {
//                    String messageText = update.getMessage().getText();
//
//                    while (cicle == false) {
//                        cityByUser = message.getText();  //получаю ответ (город) от пользователя
//                        Work work3 = new Work();
//                        cicle = work3.compareOld(cityByUser);  //проверяю по названным городам
//
//                        if (cicle == false) {
//                            sendMsg(message, "Такой город уже называли \n продолжаем вспомниать город на букву   " + Character.toUpperCase(simbol));
//
//                        }
//                    }
//
//                    Work work4 = new Work();
//                    try {
//
//                        work4.compareCityWithBase(cityByUser, oldCit);
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//            }
//        }
//
//
////
//
//
//    }


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

//    private void getUpdates(final TelegramBot bot) {
//        try {
//            GetUpdatesResponse response = bot.execute(
//                    new GetUpdates()
//                            .limit(LIMIT)
//                            .offset(updateId.get())
//                            .timeout(LONG_POLLING_TIMEOUT));
//
//            if (response != null && response.updates() != null && response.updates().size() > 0) {
//                for (Update update : response.updates()) {
//                    obtainUpdate(bot, update);
//                    updateId.set(update.updateId() + 1);
//                }
//            }
//        } catch (Exception e) {
//            ErrorUtils.log(TAG, e);
//        }
//    }


}