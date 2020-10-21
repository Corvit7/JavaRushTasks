package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.Tablet;

import java.io.IOException;
import java.util.List;

public class Order {
    private final Tablet tablet;
    protected List<Dish> dishes;

    public Order(Tablet tablet) throws IOException {
        this.tablet = tablet;
        dishes = ConsoleHelper.getAllDishesForOrder();
    }

    @Override
    public String toString() {
        return dishes.isEmpty() ? "" : "Your order: " + dishes + " of " + tablet.toString();
//        String res = null;
//        if (dishes.size()>0)
//        {
//            res = "Your order: [";
//            for (Dish dish: dishes) {
//                res += dish.name() + ", ";
//            }
//            res = res.substring(0, res.length() -2);
//            res += "] " + tablet;
//        }
//        return res;
    }
}
