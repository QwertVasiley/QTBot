# QTBot

О приложении

Приложение реализует популярную всем известную игру в ГОРОДА, когда нужно называть город, который начинается с последней буквы названного до этого города.
Игра проводится в приложении для обмена сообщениями - Telegram.
QTBot  разработал Радюк Василий Викторович

## Функционал:

Помощь по командам бота /help
Cтарт игры - пишем боту /start

После старта игры, бот выберет из базы данных рандомный город и выведет его в своем сообщении. Попросит назвать город, который заканчивается на последнюю букву им названного. 
Во время игры, если город раньше был назван – бот сообщит об этом и попросит продолжать угадывать город на ту же букву.
Если пользователь введет неверный город, то бот сообщит об этом.
За каждый верно написанный город, пользователь получает очки.

## В проекте использовал:
* Язык программирования - JAVA
* Среда разработки - IntelliJ IDEA 2019.2.4
* Java version - 10
* Базы данных - PostgreSQL 14
* Библиотека - Telegram-bot-ramework
* PostgreSQL JDBC Driver
