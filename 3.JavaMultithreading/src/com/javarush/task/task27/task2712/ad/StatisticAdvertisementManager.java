package com.javarush.task.task27.task2712.ad;

public class StatisticAdvertisementManager {

    private StatisticAdvertisementManager instance;
    private AdvertisementStorage storage;

    public StatisticAdvertisementManager( AdvertisementStorage storage) {
        this.storage = storage;
    }

    public StatisticAdvertisementManager getInstance(AdvertisementStorage storage) {
        if(instance == null)
            instance = new StatisticAdvertisementManager(storage);
        return instance;
    }

}
