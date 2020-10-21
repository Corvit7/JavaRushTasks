package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;

public enum Dish {
    Fish,
    Steak,
    Soup,
    Juice,
    Water;

    public static String allDishesToString(){
        String res = "\"";
        for (Dish dish: Dish.values()
             ) {
            res += dish.name() + ", ";
        }
        res = res.substring(0, res.length()-2);
        res += "\"";
        return res;
    }
}
