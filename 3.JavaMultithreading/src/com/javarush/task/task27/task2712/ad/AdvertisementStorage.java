package com.javarush.task.task27.task2712.ad;

import java.util.LinkedList;
import java.util.List;

public class AdvertisementStorage {
    private static AdvertisementStorage instance;
    private final List<Advertisement> videos = new LinkedList<>();

    public List<Advertisement> list(){
        return videos;
    }

    public void add(Advertisement ad) {
        videos.add(ad);
    }

    private AdvertisementStorage(){
        Object someContent = new Object();
        videos.add(new Advertisement(someContent, "First Video", 5000, 100, 3 * 60)); // 3 min
        videos.add(new Advertisement(someContent, "Second Video", 100, 10, 15 * 60)); //15 min
        videos.add(new Advertisement(someContent, "Third Video", 400, 2, 10 * 60)); //10 min
        videos.add(new Advertisement(someContent, "Fourth Video", 500, 200,  3 * 60)); //3 min
        videos.add(new Advertisement(someContent, "Fifth Video", 500, 200,  2 * 60)); //2 min
        videos.add(new Advertisement(someContent, "Sixth Video", 500, 200,  2 * 60)); //2 min
        videos.add(new Advertisement(someContent, "Seventh Video", 2000, 200,  7 * 60)); //7 min

    }

    public static AdvertisementStorage getInstance() {
        if (instance == null) {
            instance = new AdvertisementStorage();
        }
        return instance;
    }
}
