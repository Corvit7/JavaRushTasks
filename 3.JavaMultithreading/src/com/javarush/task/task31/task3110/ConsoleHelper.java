package com.javarush.task.task31.task3110;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//2.1. Вывести сообщение в консоль void writeMessage(String message)
//        2.2. Прочитать строку с консоли String readString()
//        2.3. Прочитать число с консоли int readInt()
//        Методы чтения с консоли могут бросать исключение IOException в случае ошибки ввода, учти это при их объявлении.
public class ConsoleHelper {

    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message)
    {
        System.out.println(message);
    }

    public static String readString() throws IOException
    {
        return bufferedReader.readLine();
    }


    public static int readInt() throws IOException
    {
        return Integer.parseInt(bufferedReader.readLine());
    }

//    public static void main(String[] args) throws IOException {
//        System.out.println(ConsoleHelper.readInt());
//    }
}
