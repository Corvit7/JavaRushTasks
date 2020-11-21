package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.ad.AdvertisementManager;
import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.Arrays;
import java.util.List;

public class Restaurant {

    public static void main(String[] args) {

        Cook cook = new Cook("Amigo");
        Cook cook2 = new Cook("Vitia");

        Waiter waiter = new Waiter();
        cook.addObserver(waiter);

        Tablet tablet = new Tablet(5);
        tablet.addObserver(cook);

        Tablet tablet2 = new Tablet(4);
        tablet2.addObserver(cook2);

        tablet.createOrder();
        tablet.createOrder();
        tablet2.createOrder();

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printCookWorkloading();

//        StatisticManager statisticManager = StatisticManager.getInstance();

//        tablet.createOrder();
//        tablet.createOrder();
//        tablet.createOrder();
//
//        AdvertisementManager manager = new AdvertisementManager(3 * 60);
//        manager.processVideos();

    }
}
