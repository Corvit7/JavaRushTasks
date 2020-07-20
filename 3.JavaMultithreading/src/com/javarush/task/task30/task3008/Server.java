package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

//Чат (11)
//        Пришло время написать главный метод класса Handler, который будет вызывать все вспомогательные методы, написанные ранее.
//        Реализуем метод void run() в классе Handler.
//
//        Он должен:
//        1. Выводить сообщение, что установлено новое соединение с удаленным адресом, который можно получить с помощью метода getRemoteSocketAddress().
//        2. Создавать Connection, используя поле socket.
//        3. Вызывать метод, реализующий рукопожатие с клиентом, сохраняя имя нового клиента.
//        4. Рассылать всем участникам чата информацию об имени присоединившегося участника (сообщение с типом USER_ADDED).
//        Подумай, какой метод подойдет для этого лучше всего.
//        5. Сообщать новому участнику о существующих участниках.
//        6. Запускать главный цикл обработки сообщений сервером.
//        7. Обеспечить закрытие соединения при возникновении исключения.
//        8. Отловить все исключения типа IOException и ClassNotFoundException, вывести в консоль информацию, что произошла ошибка при обмене данными с удаленным адресом.
//        9. После того как все исключения обработаны, если п.11.3 отработал и возвратил нам имя, мы должны удалить запись для этого имени из connectionMap и разослать всем остальным участникам сообщение с типом USER_REMOVED и сохраненным именем.
//        10. Последнее, что нужно сделать в методе run() - вывести сообщение, информирующее что соединение с удаленным адресом закрыто.
//
//        Наш сервер полностью готов. Попробуй его запустить.
//
//
//        Требования:
//        1. Метод run() должен выводить на экран сообщение о том, что было установлено соединение с удаленным адресом (используя метод getRemoteSocketAddress()).
//        2. Метод run() должен создавать новое соединение (connection) используя в качестве параметра поле socket.
//        3. Метод run() должен вызывать метод serverHandshake() используя в качестве параметра только что созданное соединение; результатом будет имя пользователя (userName).
//        4. Метод run() должен вызывать метод sendBroadcastMessage() используя в качестве параметра новое сообщение (MessageType.USER_ADDED, userName).
//        5. Метод run() должен вызывать метод notifyUsers() используя в качестве параметров connection и userName.
//        6. Метод run() должен вызывать метод serverMainLoop используя в качестве параметров connection и userName.
//        7. Прежде чем завершиться, метод run() должен удалять из connectionMap запись соответствующую userName, и отправлять всем участникам чата сообщение о том, что текущий пользователь был удален.
//        8. Метод run() должен корректно обрабатывать исключения IOException и ClassNotFoundException.

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
            try {
                Connection connection = new Connection(socket);
                String userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                notifyUsers(connection, userName);
                serverMainLoop(connection, userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                connectionMap.remove(userName);
                socket.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
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

//        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException
//        {
////            "Боб: привет чат".
//            while (true) {
//                if (connection.receive().getType() == MessageType.TEXT)
//                    sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + connection.receive().getData()));
//                else
//                    connection.send(new Message(MessageType.TEXT, "Неправильный тип сообщения"));
//            }
//        }

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

//        ServerSocket serverSocket = new ServerSocket();
//        Socket socket;
//        socket.

    }
}