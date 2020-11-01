package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return bufferedReader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> allDishForOrder = new ArrayList<>();
        String input;
        writeMessage("Выберете блюдо для заказа");
        writeMessage(Dish.allDishesToString());
        while (!(input = readString()).equals("exit")) {
            try {
                allDishForOrder.add(Dish.valueOf(input));
            } catch (IllegalArgumentException e) {
                ConsoleHelper.writeMessage("Выбранное блюдо отсутсвует в меню. Повторите ввод");
            }
        }
        return allDishForOrder;
    }
}
