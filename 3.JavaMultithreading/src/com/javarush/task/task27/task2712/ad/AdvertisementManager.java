package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException,UnsupportedOperationException{
//        ConsoleHelper.writeMessage(storage.list().get(0).toString());
//        throw new NoVideoAvailableException();
        if(storage.list().size() == 0)
            throw new NoVideoAvailableException();
        storage.list().get(0).revalidate();
    }
}
