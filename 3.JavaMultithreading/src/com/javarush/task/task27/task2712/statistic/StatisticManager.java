package com.javarush.task.task27.task2712.statistic;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventDataRow;
import com.javarush.task.task27.task2712.statistic.event.EventType;
import com.javarush.task.task27.task2712.statistic.event.VideoSelectedEventDataRow;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class StatisticManager {
    private static StatisticManager instance;
    private StatisticStorage statisticStorage = new StatisticStorage();
    private Set<Cook> cooks = new HashSet<>();

    public static StatisticManager getInstance()
    {
        if (instance == null)
            instance = new StatisticManager();
        return instance;
    }

    public void register (EventDataRow data) {
        statisticStorage.put(data);
    }
    public void register(Cook cook){
        cooks.add(cook);
    }

    public Map<String, Double> getAdsProfit() {
        return statisticStorage.calculateAdvertisementProfit();
    }

    public Map<String, Map<String, Integer>> getCooksWorkload() {
        return statisticStorage.calculateWorkload();
    }

    private class StatisticStorage {
        private Map<EventType, List<EventDataRow>> storage = new HashMap<>();
        private Map<String, Double> adsProfit;
        private Map<String, Map<String, Integer>> cooksWorkload;


        public StatisticStorage() {
            for (EventType eventType :
                    EventType.values()) {
                storage.put(eventType, new ArrayList<EventDataRow>());
            }
        }

        private void put(EventDataRow data)
        {
            storage.get(data.getType()).add(data);
        }

        public Map<String, Double> calculateAdvertisementProfit()
        {
            adsProfit = new TreeMap<>(Collections.reverseOrder());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            for (EventDataRow video: storage.get(EventType.SELECTED_VIDEOS)) {
                VideoSelectedEventDataRow videoRow = ((VideoSelectedEventDataRow) video);
                double profit = videoRow.getAmount();
                String date = sdf.format(videoRow.getDate());
                if (adsProfit.containsKey(date)) {
                    adsProfit.put(date, adsProfit.get(date) + profit/100.00);
                }
                else {
                    adsProfit.put(date, profit/100.00);
                }
            }
            return adsProfit;
        }

        public Map<String, Map<String, Integer>> calculateWorkload(){
            cooksWorkload = new TreeMap<>(Collections.reverseOrder());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            for(EventDataRow order: storage.get(EventType.COOKED_ORDER)){
                CookedOrderEventDataRow cookedOrder = ((CookedOrderEventDataRow) order);
                String cookName = cookedOrder.getCookName();
                Map<String, Integer> cooks;
                Integer time = cookedOrder.getTime();
                String date = sdf.format(cookedOrder.getDate());
                if(cooksWorkload.containsKey(date)){
                    cooks = cooksWorkload.get(date);
                    if(cooks.containsKey(cookName)){
                        cooks.put(cookName, cooks.get(cookName) + time);
                    }
                    else {
                        cooks.put(cookName, time);
                    }
                }
                else
                {
                    cooks = new TreeMap<>();
                    cooks.put(cookName, time);
                }
                cooksWorkload.put(date, cooks);
            }
            return cooksWorkload;
        }
    }

}
