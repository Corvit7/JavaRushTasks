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

//        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
//
//            Message inpMessage = null;
//            Message outMessage = null;
//            String userName = "";
//            boolean flg = false;
//            do {
//                connection.send(new Message(MessageType.NAME_REQUEST, "Введите имя пользователя: "));
//                inpMessage = connection.receive();
//                if (inpMessage.getType() == MessageType.USER_NAME
//                        || !inpMessage.getData().isEmpty()
//                        ) {
//                    userName = inpMessage.getData();
//                    if (connectionMap.containsKey(userName))
//                        outMessage = new Message(MessageType.TEXT, "Такой пользователь уже зарегестрирован");
//                    else {
//                        outMessage = new Message(MessageType.NAME_ACCEPTED);
//                        flg = true;
//                        connectionMap.put(userName, connection);
//                        connection.send(outMessage);
//                    }
//                } else
//                    outMessage = new Message(MessageType.TEXT, "Ожидается сообщение типа" + MessageType.USER_NAME);
//            } while (!flg);
//            return userName;
//        }

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
//            for (Map.Entry<String, Connection> entry: connectionMap.entrySet()
//                 ) {
//                if(!userName.equals(entry.getKey()))
//                    try {
//                        connection.send(new Message(MessageType.USER_ADDED, userName));
//                    } catch (IOException e)
//                    {
//                        e.printStackTrace();
//                    }
//            }
        }


    }
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        ConsoleHelper.writeMessage("Введите номер порта на котором хотите поднять сервер6");
        ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt());
        System.out.println("Server Started");
        try {
            while (true){
                ConsoleHelper.writeMessage("ожидаю новых подключений. нажмите любую клавишу");
                socket = serverSocket.accept();
                Handler handler = new Handler(socket);
                handler.start();
            }
        } catch (IOException e) {
            System.out.println(e);
            serverSocket.close();
        }

//        ServerSocket serverSocket = new ServerSocket();
//        Socket socket;
//        socket.

    }
}