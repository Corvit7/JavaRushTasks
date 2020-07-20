package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;


//Чат (7)
//        Т.к. сервер может одновременно работать с несколькими клиентами, нам понадобится метод для отправки сообщения сразу всем.
//
//        Добавь в класс Server:
//
//        1. Статическое поле Map<String, Connection> connectionMap, где ключом будет имя клиента, а значением - соединение с ним.
//        2. Инициализацию поля из п.7.1 с помощью подходящего Map из библиотеки java.util.concurrent, т.к. работа с этим полем будет происходить из разных потоков и нужно обеспечить потокобезопасность.
//        3. Статический метод void sendBroadcastMessage(Message message), который должен отправлять сообщение message всем соединениям из connectionMap.
//        Если при отправке сообщение произойдет исключение IOException, нужно отловить его и сообщить пользователю, что не смогли отправить сообщение.
//
//
//        Требования:
//        1. В классе Server должно существовать статическое приватное поле connectionMap типа Map.
//        2. Поле connectionMap должно быть инициализировано потокобезопасной реализаций интерфейса Map из пакета java.util.concurrent.
//        3. В классе Server должен быть корректно реализован статический метод sendBroadcastMessage(Message message), отправляющий сообщение всем соединениям из connectionMap.

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
    }
    public static void main(String[] args) throws IOException {
        Socket socket = null;
        ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt());
        System.out.println("Server Started");
        try {
            while (true){
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