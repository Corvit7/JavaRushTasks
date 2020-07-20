package com.javarush.task.task30.task3008.client;


import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;

public class Client {
    protected Connection connection;
    private volatile boolean clientConnected = false;
    public class SocketThread extends Thread
    {

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

//    Требования:
//            1. Метод getServerAddress() должен возвращать строку (адрес сервера), считанную с консоли.
//2. Метод getServerPort() должен возвращать число (порт сервера), считанное с консоли.
//3. Метод getUserName() должен возвращать строку (имя пользователя), считанную с консоли.
//4. Метод shouldSendTextFromConsole() должен возвращать true.
//            5. Метод sendTextMessage() должен создавать и отправлять новое текстовое сообщение используя connection и устанавливать флаг clientConnected в false, если во время отправки или создания сообщения возникло исключение IOException.
//            6. Метод getSocketThread() должен возвращать новый объект типа SocketThread.


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
}
