package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.StatisticManager;

import java.util.*;

public class DirectorTablet {

    public void printAdvertisementProfit(){
        StatisticManager statisticManager = StatisticManager.getInstance();
        Map<String, Double> adsProfit = statisticManager.getAdsProfit();
        Double totalAmount = 0.0;
        List<String> dateList = new LinkedList<>();
        adsProfit.forEach((String date, Double amount) -> {
            dateList.add(date);
        });
        for (String date: dateList
             ) {
            Double amount = adsProfit.get(date);
            ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "%s - %.2f", date, amount));
            totalAmount += amount;
        }
        ConsoleHelper.writeMessage(String.format(Locale.ENGLISH, "Total - %.2f", totalAmount));
    }
    public void printCookWorkloading() {
        StatisticManager statisticManager = StatisticManager.getInstance();
        Map<String, Map<String, Integer>> cooksWorkload = statisticManager.getCooksWorkload();
        for (Map.Entry<String, Map<String, Integer>> load : cooksWorkload.entrySet()) {
            ConsoleHelper.writeMessage(load.getKey());
            for (Map.Entry<String, Integer> inner : load.getValue().entrySet()) {
                //    int workTime = (int) Math.ceil(inner.getValue() / 60.0);
                ConsoleHelper.writeMessage(String.format("%s - %d min", inner.getKey(), inner.getValue()));
            }
            ConsoleHelper.writeMessage("");
        }
    }
    public void printActiveVideoSet() {}
    public void printArchivedVideoSet() {}

}
