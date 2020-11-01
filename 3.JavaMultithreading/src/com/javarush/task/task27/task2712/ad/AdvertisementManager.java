package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException {

        /*
    2.2. Подобрать список видео из доступных, просмотр которых обеспечивает максимальную выгоду.
    (Пока делать не нужно, сделаем позже).
    2.4. Отобразить все рекламные ролики, отобранные для показа, в порядке уменьшения стоимости
    показа одного рекламного ролика в копейках.
    Вторичная сортировка - по увеличению стоимости показа одной секунды рекламного ролика в
    тысячных частях копейки. Используйте метод Collections.sort
         */
        if (storage.list().isEmpty()) throw new NoVideoAvailableException();
        else {
            AdvertisementPack advertisementPack = new AdvertisementPack(timeSeconds);
            advertisementPack.createAdvertisementPack(storage.list().stream()
                    .filter(o -> o.getHits() > 0)
                    .filter(o -> o.getDuration() <= timeSeconds)
                    .collect(Collectors.toList()));

            /*
            Если существует несколько вариантов набора видео-роликов с одинаковой суммой денег,
            полученной от показов, то должен быть выбран вариант с максимальным суммарным временем.
            5. Если существует несколько вариантов набора видео-роликов с одинаковой суммой денег и
            одинаковым суммарным временем, то должен быть выбран вариант с минимальным количеством роликов
             */
            Comparator<AdvertisementPack> sortByProfit = (p, o) -> -(int) (p.totalProfit - o.totalProfit);
            Comparator<AdvertisementPack> sortByDuration = (p, o) -> (o.totalDuration - p.totalDuration);
            Comparator<AdvertisementPack> sortByCountAdvertisement = Comparator.comparingInt(p -> p.countAdvertisement);
            Comparator<Advertisement> sortAmountPerOneDisplaying = (p, o) -> -(int) (p.getAmountPerOneDisplaying() - o.getAmountPerOneDisplaying());
            Comparator<Advertisement> sortAmountPerOneSeconds = (p, o) -> (int) (p.getAmountPerOneSeconds() - o.getAmountPerOneSeconds());

            List<AdvertisementPack> advertisementsPackList = advertisementPack.getAdvertisementPacks().stream().
                    sorted((sortByProfit).
                            thenComparing(sortByDuration).
                            thenComparing(sortByCountAdvertisement)).collect(Collectors.toList());
//            advertisementsPackList.forEach(System.out::println);

            advertisementsPackList.get(0).getAdvertisementList().stream()
                    .sorted((sortAmountPerOneDisplaying)
                            .thenComparing(sortAmountPerOneSeconds)).

                    forEach(o -> {
//                       timeSeconds=timeSeconds-o.getDuration();
                        ConsoleHelper.writeMessage(o.toString());
                        o.revalidate();

//                       System.out.println(timeSeconds);
                    });


        }
    }

    private class AdvertisementPack {
        private List<AdvertisementPack> advertisementPacks = new ArrayList<>();
        private int timeSeconds;
        private int totalDuration;                      //хронометраж роликов в листе в секунда
        private long totalProfit;                       //стоимость показа листа в копейках
        private int countAdvertisement;                 //количество роликов в листе
        private List<Advertisement> advertisementList;  //лист с роликами

        public AdvertisementPack(int timeSeconds) {
            this.timeSeconds = timeSeconds;
        }

        private AdvertisementPack(int totalDuration, long totalProfit, int countAdvertisement, List<Advertisement> advertisementList) {
            this.totalDuration = totalDuration;
            this.totalProfit = totalProfit;
            this.countAdvertisement = countAdvertisement;
            this.advertisementList = advertisementList;
        }

        public void createAdvertisementPack(List<Advertisement> advertisements) {
            if (countTotalDuration(advertisements) <= timeSeconds || advertisements.size() <= 1) {

                AdvertisementPack addToList = new AdvertisementPack(countTotalDuration(advertisements),
                        countTotalProfit(advertisements), advertisements.size(), advertisements);
                if (!advertisementPacks.contains(addToList)) advertisementPacks.add(addToList);

            } else {
                for (int i = 0; i < advertisements.size(); i++) {
                    List<Advertisement> newAdvertisements = new ArrayList<>(advertisements);
                    newAdvertisements.remove(i);
                    createAdvertisementPack(newAdvertisements);
                }
            }
        }

        private int countTotalDuration(List<Advertisement> advertisements) {
            return advertisements.stream().mapToInt(Advertisement::getDuration).sum();
        }

        private long countTotalProfit(List<Advertisement> advertisements) {
            return advertisements.stream().mapToLong(Advertisement::getAmountPerOneDisplaying).sum();
        }

        public List<AdvertisementPack> getAdvertisementPacks() {
            return advertisementPacks;
        }

        public List<Advertisement> getAdvertisementList() {
            return advertisementList;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AdvertisementPack that = (AdvertisementPack) o;
            return totalDuration == that.totalDuration &&
                    totalProfit == that.totalProfit &&
                    countAdvertisement == that.countAdvertisement;
        }

        @Override
        public int hashCode() {
            return Objects.hash(totalDuration, totalProfit, countAdvertisement);
        }

        @Override
        public String toString() {
            return "AdvertisementPack{" +
                    "Стоимость=" + totalProfit +
                    ", Продолжительность=" + totalDuration +

                    ", Количество роликов=" + countAdvertisement +
                    '}';
        }

    }
}