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

//    Чат (16)
//    Теперь все готово, чтобы дописать необходимые методы класса SocketThread.
//
//            1) Добавь protected метод clientHandshake() throws IOException, ClassNotFoundException.
//            Этот метод будет представлять клиента серверу.
//
//    Он должен:
//    а) В цикле получать сообщения, используя соединение connection.
//    б) Если тип полученного сообщения NAME_REQUEST (сервер запросил имя), запросить ввод имени пользователя с помощью метода getUserName(), создать новое сообщение с типом MessageType.USER_NAME и введенным именем, отправить сообщение серверу.
//    в) Если тип полученного сообщения MessageType.NAME_ACCEPTED (сервер принял имя), значит сервер принял имя клиента, нужно об этом сообщить главному потоку, он этого очень ждет.
//    Сделай это с помощью метода notifyConnectionStatusChanged(), передав в него true.
//    После этого выйди из метода.
//            г) Если пришло сообщение с каким-либо другим типом, кинь исключение IOException("Unexpected MessageType").
//
//            2) Добавь protected метод void clientMainLoop() throws IOException, ClassNotFoundException.
//
//            Этот метод будет реализовывать главный цикл обработки сообщений сервера. Внутри метода:
//    а) Получи сообщение от сервера, используя соединение connection.
//    б) Если это текстовое сообщение (тип MessageType.TEXT), обработай его с помощью метода processIncomingMessage().
//    в) Если это сообщение с типом MessageType.USER_ADDED, обработай его с помощью метода informAboutAddingNewUser().
//    г) Если это сообщение с типом MessageType.USER_REMOVED, обработай его с помощью метода informAboutDeletingNewUser().
//    д) Если клиент получил сообщение какого-либо другого типа, брось исключение IOException("Unexpected MessageType").
//    е) Размести код из предыдущих пунктов внутри бесконечного цикла.
//    Цикл будет завершен автоматически если произойдет ошибка (будет брошено исключение) или поток, в котором работает цикл, будет прерван.
//
//
//    Требования:
//            1. Метод clientHandshake() должен отправлять новое сообщение (MessageType.USER_NAME, getUserName()) используя connection, если тип принятого сообщения равен MessageType.NAME_REQUEST.
//2. Метод clientHandshake() должен вызывать метод notifyConnectionStatusChanged(true) и завершаться, если тип принятого сообщения равен MessageType.NAME_ACCEPTED.
//3. Метод clientHandshake() должен бросать исключение IOException, если тип принятого сообщения не MessageType.NAME_ACCEPTED или не MessageType.NAME_REQUEST.
//4. Метод clientHandshake() должен принимать сообщения от сервера до тех пор, пока тип сообщения равен MessageType.NAME_REQUEST.
//5. Метод clientMainLoop() должен принимать сообщения от сервера до тех пор, пока тип сообщения равен MessageType.TEXT, MessageType.USER_REMOVED или MessageType.USER_ADDED.
//6. Метод clientMainLoop() должен обрабатывать полученное сообщение с помощью метода processIncomingMessage(), если тип принятого сообщения равен MessageType.TEXT.
//7. Метод clientMainLoop() должен обрабатывать полученное сообщение с помощью метода informAboutAddingNewUser(), если тип принятого сообщения равен MessageType.USER_ADDED.
//8. Метод clientMainLoop() должен обрабатывать полученное сообщение с помощью метода informAboutDeletingNewUser(), если тип принятого сообщения равен MessageType.USER_REMOVED.
//9. Метод clientMainLoop() должен бросать исключение IOException, если тип принятого сообщения не MessageType.TEXT, MessageType.USER_REMOVED или не MessageType.USER_ADDED.
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

    }

}
