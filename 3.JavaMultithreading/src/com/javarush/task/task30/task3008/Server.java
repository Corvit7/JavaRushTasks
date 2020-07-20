package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Server {
    
    private static Map<String, Connection> connectionMap = new java.util.concurrent.ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message) {
        for (String name : connectionMap.keySet()) {
            try {
                connectionMap.get(name).send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage(String.format("Can't send the message to %s", name));
            }
        }
    }

    private static class Handler extends Thread {

        private Socket socket;

        public Handler(Socket socket){
            this.socket = socket;
        }

        @Override
        public void run() {
            ConsoleHelper.writeMessage(socket.getRemoteSocketAddress().toString());
            String userName = null;
            try {
                Connection connection = new Connection(socket);
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                socket.close();
            } catch (IOException | ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            Message messageOut = new Message(MessageType.NAME_REQUEST);
            Message messageIn;
            String name = null;

            do {
                connection.send(messageOut); //отправляем команду запроса имени пользователя
                messageIn = connection.receive(); //получаем ответ от клиента
                name = messageIn.getData();

            }while (messageIn.getType() != MessageType.USER_NAME || name.isEmpty() || connectionMap.containsKey(name));
            connectionMap.put(name, connection);
            connection.send(new Message(MessageType.NAME_ACCEPTED));

            return name;
        }

        private void notifyUsers(Connection connection, String userName) throws IOException {

            connectionMap.forEach((k,v)-> {
                        try {
                            if (!k.equals(userName)) connection.send(new Message(MessageType.USER_ADDED, k));
                        } catch (IOException e) {
                        }
                    });
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {

            //Организовать бесконечный цикл
            while (true){
                // Принимать сообщение клиента
                Message message = connection.receive();

                // Если принятое сообщение – это текст
                if (message.getType() == MessageType.TEXT){
                    //  формировать новое текстовое сообщение
                    String messageText = userName + ": " + message.getData();
                    // Отправлять сформированное сообщение всем клиентам с помощью
                    sendBroadcastMessage(new Message(MessageType.TEXT, messageText));
                }
                else {
                    // Если принятое сообщение не является текстом, вывести сообщение об ошибке
                    ConsoleHelper.writeMessage("Error!");
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        ConsoleHelper.writeMessage("Введите номер порта на котором хотите поднять сервер");
        ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt());
        System.out.println("Server Started");
        try {
            while (true){
                ConsoleHelper.writeMessage("ожидаю новых подключений");
                socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        } catch (IOException e) {
            System.out.println(e);
            serverSocket.close();
        }

    }
}