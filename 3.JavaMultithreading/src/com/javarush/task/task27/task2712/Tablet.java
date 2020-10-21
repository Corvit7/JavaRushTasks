package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Order;

import javax.swing.text.html.HTML;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tablet {
    private final int number;
    private static Logger logger = Logger.getLogger(Tablet.class.getName());

    public Tablet(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void createOrder(){
        try {
            Order order = new Order(this);
            System.out.println(order);
        } catch (IOException e)
        {
            logger.log(Level.SEVERE, "Console is unavailable.");
        }
    }

    @Override
    public String toString() {
        return "Tablet{" +
                "number=" + number +
                '}';
    }
}
