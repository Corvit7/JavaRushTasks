package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

import java.util.Arrays;
import java.util.List;

public class Restaurant {

    public static void main(String[] args) {

        Cook cook = new Cook("Amigo");

        Waiter waiter = new Waiter();
        cook.addObserver(waiter);

        Tablet tablet = new Tablet(5);
        tablet.addObserver(cook);


        tablet.createOrder();
//        tablet.createOrder();
//        tablet.createOrder();
//        tablet.createOrder();
//
//        AdvertisementManager manager = new AdvertisementManager(3 * 60);
//        manager.processVideos();

    }
}
