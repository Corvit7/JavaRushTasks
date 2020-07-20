package com.javarush.task.task30.task3008.client;


import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;

public class Client {


//    Чат (14)
//    Приступим к написанию главного функционала класса Client.
//
//1. Добавь метод public void run().
//    Он должен создавать вспомогательный поток SocketThread, ожидать пока тот установит соединение с сервером, а после этого в цикле считывать сообщения с консоли и отправлять их серверу.
//    Условием выхода из цикла будет отключение клиента или ввод пользователем команды 'exit'.
//    Для информирования главного потока, что соединение установлено во вспомогательном потоке, используй методы wait() и notify() объекта класса Client.
//
//    Реализация метода run должна:
//    а) Создавать новый сокетный поток с помощью метода getSocketThread().
//    б) Помечать созданный поток как daemon, это нужно для того, чтобы при выходе из программы вспомогательный поток прервался автоматически.
//    в) Запустить вспомогательный поток.
//            г) Заставить текущий поток ожидать, пока он не получит нотификацию из другого потока.
//            Подсказка: используй wait() и синхронизацию на уровне объекта.
//    Если во время ожидания возникнет исключение, сообщи об этом пользователю и выйди из программы.
//            д) После того, как поток дождался нотификации, проверь значение clientConnected.
//    Если оно true - выведи "Соединение установлено.
//    Для выхода наберите команду 'exit'.".
//    Если оно false - выведи "Произошла ошибка во время работы клиента.".
//    е) Считывай сообщения с консоли пока клиент подключен.
//    Если будет введена команда 'exit', то выйди из цикла.
//    ж) После каждого считывания, если метод shouldSendTextFromConsole() возвращает true, отправь считанный текст с помощью метода sendTextMessage().
//
//            2. Добавь метод public static void main(String[] args).
//    Он должен создавать новый объект типа Client и вызывать у него метод run().
//
//
//    Требования:
//            1. Метод main() должен создавать новый объект типа Client и вызывать у него метод run().
//            2. Метод run() должен создавать и запускать новый поток, полученный с помощью метода getSocketThread().
//            3. Поток созданный с помощью метода getSocketThread() должен быть отмечен как демон (setDaemon(true)).
//            4. После запуска нового socketThread метод run() должен ожидать до тех пор, пока не будет пробужден.
//            5. До тех пор, пока флаг clientConnected равен true,с консоли должны считываться сообщения с помощью метода ConsoleHelper.readString().
//            6. Если была введена команда "exit" цикл считывания сообщений должен быть прерван.
//            7. Если метод shouldSentTextFromConsole() возвращает true, должен быть вызван метод sendTextMessage() со считанным текстом в качестве параметра.

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

    public class SocketThread extends Thread{}

}
