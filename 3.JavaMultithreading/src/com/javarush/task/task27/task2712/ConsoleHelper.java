package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;
import com.javarush.task.task27.task2712.kitchen.Order;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return ConsoleHelper.bufferedReader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException{
        ConsoleHelper.writeMessage("Мы подаем следующие блюда");
        ConsoleHelper.writeMessage(Dish.allDishesToString());
        ConsoleHelper.writeMessage("Введите название блюда, которое хотите заказать");
        ConsoleHelper.writeMessage("Для завершения заказа введить \"exit\"");

        String inp="";
        List<Dish> dishes = new LinkedList<>();
        int i = 0;
        while (true)
        {
            inp = ConsoleHelper.readString();
            if(inp.equals("exit"))
                break;
            if(Dish.allDishesToString().contains(inp))
            {
                for (Dish dish: Dish.values()) {
                    if(dish.name().equals(inp)) {
                        dishes.add(i, dish);
                        ConsoleHelper.writeMessage("Блюдо добавлено в заказ");
                        i++;
                    }
                }
            }
            else
                ConsoleHelper.writeMessage("Такого блюда нет в меню");
        }
        return dishes;
    }
}
