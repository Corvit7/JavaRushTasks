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

//        videos.add(new Advertisement(someContent, "1", 90000, 1, 3 * 60));
//        videos.add(new Advertisement(someContent, "2", 90000, 2, 3 * 60));
//        videos.add(new Advertisement(someContent, "3", 90000, 3, 3 * 60));
//        videos.add(new Advertisement(someContent, "4", 90000, 4, 3 * 60));
//        videos.add(new Advertisement(someContent, "5", 90000, 5, 3 * 60));
//        videos.add(new Advertisement(someContent, "6", 90000, 6, 3 * 60));
//        videos.add(new Advertisement(someContent, "7", 90000, 7, 3 * 60));
//        videos.add(new Advertisement(someContent, "8", 90000, 8, 3 * 60));
//        videos.add(new Advertisement(someContent, "9", 90000, 9, 3 * 60));
//        videos.add(new Advertisement(someContent, "10", 90000, 10, 3 * 60));
//        videos.add(new Advertisement(someContent, "11", 90000, 11, 3 * 60));
//        videos.add(new Advertisement(someContent, "12", 90000, 12, 3 * 60));

    }

    public static AdvertisementStorage getInstance() {
        if (instance == null) {
            instance = new AdvertisementStorage();
        }
        return instance;
    }
}
