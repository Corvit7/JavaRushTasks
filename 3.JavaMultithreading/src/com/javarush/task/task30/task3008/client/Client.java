package com.javarush.task.task30.task3008.client;


import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;
import sun.misc.Cleaner;

import java.io.IOException;

public class Client {

    protected Connection connection;
    private volatile boolean clientConnected = false;

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    protected String getServerAddress()
    {
        ConsoleHelper.writeMessage("Укажите ip сервера к которому хотите подключиться или localhost");
        return ConsoleHelper.readString();
    }

    protected int getServerPort() {
        ConsoleHelper.writeMessage("Укажите порт сервера к которому хотите подключиться");
        return ConsoleHelper.readInt();
    }

    protected String getUserName()
    {
        ConsoleHelper.writeMessage("Представьтесь. Введите имя пользователя");
        return ConsoleHelper.readString();
    }

    protected boolean shouldSendTextFromConsole()
    {
        return true;
    }

    protected void sendTextMessage(String text) {
        try {
            Message message = new Message(MessageType.TEXT, text);
            connection.send(message);
        } catch (IOException e) {
//            ConsoleHelper.writeMessage("Не удалось отправить сообщение");
            clientConnected = false;
        }
    }

    protected SocketThread getSocketThread(){
        return new SocketThread();
    }


//    public void run() {
//        SocketThread socketThread = new SocketThread();
//        socketThread.setDaemon(true);
//        socketThread.start();
//
//        synchronized (this) {
//
//            while (clientConnected) {
//                String inp = ConsoleHelper.readString();
//                if (inp.equals("exit"))
//                    clientConnected = false;
//                if (shouldSendTextFromConsole())
//                    sendTextMessage(inp);
//            }
//        }
//    }

    public void run(){
        SocketThread socketThread = getSocketThread();
        socketThread.setDaemon(true);
        socketThread.start();
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                ConsoleHelper.writeMessage("Во время ожидания возникло исключение.");
                return;
            }
        }
        if(clientConnected){
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
        }else ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        while (clientConnected){
            String data = ConsoleHelper.readString();
            if(data.equals("exit")){
                break;
            }else{
                if(shouldSendTextFromConsole())sendTextMessage(data);
            }
        }

    }

//    Чат (15)
//    Напишем реализацию класса SocketThread.
//    Начнем с простых вспомогательных методов.
//
//    Добавь методы, которые будут доступны классам потомкам и не доступны остальным классам вне пакета:
//            1) void processIncomingMessage(String message) - должен выводить текст message в консоль.
//            2) void informAboutAddingNewUser(String userName) - должен выводить в консоль информацию о том, что участник с именем userName присоединился к чату.
//            3) void informAboutDeletingNewUser(String userName) - должен выводить в консоль, что участник с именем userName покинул чат.
//            4) void notifyConnectionStatusChanged(boolean clientConnected) - этот метод должен:
//    а) Устанавливать значение поля clientConnected внешнего объекта Client в соответствии с переданным параметром.
//    б) Оповещать (пробуждать ожидающий) основной поток класса Client.
//
//    Подсказка: используй синхронизацию на уровне текущего объекта внешнего класса и метод notify().
//    Для класса SocketThread внешним классом является класс Client.
//
//
//    Требования:
//            1. Метод processIncomingMessage() должен выводить на экран сообщение полученное в качестве параметра.
//            2. Метод informAboutAddingNewUser() должен выводить на экран сообщение о том что пользователь подключился к чату.
//            3. Метод informAboutDeletingNewUser() должен выводить на экран сообщение о том что пользователь покинул чат.
//            4. Метод notifyConnectionStatusChanged() должен устанавливать значение поля clientConnected внешнего объекта Client равным полученному параметру.
//            5. Метод notifyConnectionStatusChanged() должен вызвать метод notify() на внешнем объекте Client.
    public class SocketThread extends Thread{
        protected void processIncomingMessage(String message)
        {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName)
        {
            ConsoleHelper.writeMessage(userName + "присоединился к чату");
        }

        protected void informAboutDeletingNewUser(String userName)
        {
            ConsoleHelper.writeMessage(userName + "покинлу чат");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected)
        {
            synchronized (Client.this) {
                Client.this.clientConnected = clientConnected;
                Client.this.notify();
            }
        }

    }

}
