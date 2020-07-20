package com.javarush.task.task30.task3008.client;


import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;
import sun.misc.Cleaner;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

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

    public class SocketThread extends Thread{
        protected void processIncomingMessage(String message)
        {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName)
        {
            ConsoleHelper.writeMessage(userName + " присоединился к чату");
        }

        protected void informAboutDeletingNewUser(String userName)
        {
            ConsoleHelper.writeMessage(userName + " покинул чат");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected)
        {
            synchronized (Client.this) {
                Client.this.clientConnected = clientConnected;
                Client.this.notify();
            }
        }

        protected void clientHandshake() throws IOException, ClassNotFoundException
        {

            while (true)
            {
                Message message;
                message = connection.receive();
                if(message.getType() == MessageType.NAME_REQUEST)
                {
                    connection.send(new Message(MessageType.USER_NAME, getUserName()));
                }

                else if (message.getType() == MessageType.NAME_ACCEPTED)
                {
                    notifyConnectionStatusChanged(true);
                    break;
                }

                else
                    throw new IOException("Unexpected MessageType");
            }
        }

        protected void clientMainLoop() throws IOException, ClassNotFoundException
        {
            while (true) {
                Message message;
                message = Client.this.connection.receive();
                if(message.getType() == MessageType.TEXT)
                    processIncomingMessage(message.getData());
                else if(message.getType() == MessageType.USER_ADDED)
                    informAboutAddingNewUser(message.getData());
                else if(message.getType() == MessageType.USER_REMOVED)
                    informAboutDeletingNewUser(message.getData());
                else
                    throw new IOException("Unexpected MessageType");
            }
        }

//    Чат (17)
//    Последний, но самый главный метод класса SocketThread - это метод void run().
//    Добавь его. Его реализация с учетом уже созданных методов выглядит очень просто.
//
//    Давай напишем ее:
//            1) Запроси адрес и порт сервера с помощью методов getServerAddress() и getServerPort().
//            2) Создай новый объект класса java.net.Socket, используя данные, полученные в предыдущем пункте.
//3) Создай объект класса Connection, используя сокет из п.17.2.
//            4) Вызови метод, реализующий "рукопожатие" клиента с сервером (clientHandshake()).
//            5) Вызови метод, реализующий основной цикл обработки сообщений сервера.
//6) При возникновении исключений IOException или ClassNotFoundException сообщи главному потоку о проблеме, используя notifyConnectionStatusChanged() и false в качестве параметра.
//
//    Клиент готов, можешь запустить сервер, несколько клиентов и проверить как все работает.
//
//
//    Требования:
//            1. В методе run() должно быть установлено и сохранено в поле connection соединение с сервером (для получения адреса сервера и порта используй методы getServerAddress() и getServerPort()).
//            2. В методе run() должен быть вызван метод clientHandshake().
//            3. В методе run() должен быть вызван метод clientMainLoop().
//            4. При возникновении исключений IOException или ClassNotFoundException в процессе работы метода run(), должен быть вызван метод notifyConnectionStatusChanged() с параметром false.
//            5. Заголовок метода run() должен соответствовать условию задачи.


//    @Override
//    public void run() {
//        try {
//            Socket socket = new Socket(getServerAddress(), getServerPort());
//            Connection connection = new Connection(socket);
//            clientHandshake();
//            clientMainLoop();
//        } catch (UnknownHostException e)
//        {
//            e.printStackTrace();
//        }
//        catch (IOException e)
//        {
//            notifyConnectionStatusChanged(false);
//            e.printStackTrace();
//        }
//        catch (ClassNotFoundException e)
//        {
//            notifyConnectionStatusChanged(false);
//            e.printStackTrace();
//        }
//    }

        @Override
        public void run() {
            String adressServer = getServerAddress();
            int port = getServerPort();

            Socket socket = null;

            try {
                socket = new Socket(adressServer, port);
                Connection connection = new Connection(socket);
                Client.this.connection = connection;
                    clientHandshake();
                    clientMainLoop();

            } catch (IOException e) {
//                e.printStackTrace( );
                notifyConnectionStatusChanged(false);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                notifyConnectionStatusChanged(false);
            }
        }

}

}
