package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static class Handler extends Thread {

        private Socket socket;

        public Handler(Socket socket){
            this.socket = socket;
        }
    }
    public static void main(String[] args) throws IOException {
        Socket socket;
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