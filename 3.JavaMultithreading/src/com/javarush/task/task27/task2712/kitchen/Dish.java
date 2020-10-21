package com.javarush.task.task27.task2712.kitchen;

import java.util.Arrays;

public enum Dish {

    Fish(25),
    Steak(30),
    Soup(15),
    Juice(5),
    Water(3);

    private int duration;

    public int getDuration() {
        return duration;
    }

    Dish(int duration) {
        this.duration = duration;
    }

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
