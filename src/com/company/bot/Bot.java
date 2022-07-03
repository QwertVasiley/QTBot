package com.company.bot;

import com.company.model.DBCityModel;
import com.company.model.DBOldCityModel;
import com.company.utils.BotConfig;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


import java.util.ArrayList;



public class Bot extends TelegramLongPollingBot {

    ArrayList<DBCityModel> cit = new ArrayList<>(); //для работы с полной базой
    ArrayList<DBOldCityModel> oldCit = new ArrayList<>(); // для работы с названными городами
    char charLastLeter; //последняя буква названии города
    String cityByUser;
    String myCity;

    String name = "";

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            if (message.getText().equals("/help")) {
                sendMsg(message, "Привет, я бот игры 'Города' \n для старта игры используй /start \n лучшая пятёрка игроков /top5 \n лучшая десятка игроков /top10 \n информация о твоей игре /info");
            }
            if (message.getText().equals("/top10")) {
                sendMsg(message, "Тут вывелась десятка лучших игроков");
            }
            if (message.getText().equals("/top5")) {
                sendMsg(message, "Тут вывелась пятерка лучших игроков");
            }
            if (message.getText().equals("/info")) {
                sendMsg(message, "Тут вывелась инфо о твоей игре и количество набраных очков");
            }
            if (message.getText().equals("/старт") || message.getText().equals("/start")) { // тут будет старт игры

                Work work = new Work();
                int number = work.amount();  //получаю кол-во городов
                sendMsg(message, "В базе  " + number + " городов ");

                Work work1 = new Work(); //получаю рандомный город
                String rndCity = work.rand();

                sendMsg(message, "Старт игры, я начну первым и мой город:    " + rndCity);

                Work work2 = new Work();
                char simbol = work2.findBeadLastLiter(rndCity);
                sendMsg(message, "Вспоминаем город на букву  " + Character.toUpperCase(simbol));


                if (message.hasText()) {
                    String messageText = update.getMessage().getText();
                    sendMsg(message, "Ты назвал город " + messageText);
                }
            }
        }
//


        boolean cicle = false;
        while (cicle = false) {
            cityByUser = message.getText();  //получаю ответ (город) от пользователя
            Work work3 = new Work();
            cicle = work3.compareOld(cityByUser);
            sendMsg(message, "Ты назвал город " + cityByUser);
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