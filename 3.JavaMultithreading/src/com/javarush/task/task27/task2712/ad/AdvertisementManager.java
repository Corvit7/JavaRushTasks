package com.javarush.task.task27.task2712.ad;

import com.javarush.task.task27.task2712.ConsoleHelper;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class AdvertisementManager {
    private final AdvertisementStorage storage = AdvertisementStorage.getInstance();
    private int timeSeconds;

    private static List<List<Advertisement>> lists = new LinkedList<>();

    public AdvertisementManager(int timeSeconds) {
        this.timeSeconds = timeSeconds;
    }

    public void processVideos() throws NoVideoAvailableException,UnsupportedOperationException{
//        ConsoleHelper.writeMessage(storage.list().get(0).toString());
//        throw new NoVideoAvailableException();
//        if(storage.list().size() == 0)
//            throw new NoVideoAvailableException();
//        storage.list().get(0).revalidate();

        allCombinations(storage.list());

//        System.out.println(AdvertisementManager.lists.size());
        //сортировка по убыванию доходности
        Collections.sort(lists, new Comparator<List<Advertisement>>() {
            @Override
            public int compare(List<Advertisement> o1, List<Advertisement> o2) {
                int profit1 = 0;
                int profit2 = 0;
                for (Advertisement ad: o1
                     ) {
                    profit1 += ad.getAmountPerOneDisplaying();
                };
                for (Advertisement ad: o2
                ) {
                    profit2 += ad.getAmountPerOneDisplaying();
                };
                return profit2 - profit1;
            }
        }.thenComparing(new Comparator<List<Advertisement>>() {
            @Override
            public int compare(List<Advertisement> o1, List<Advertisement> o2) {
                int overalDuration1 = 0;
                int overalDuration2 = 0;
                for (Advertisement ad: o1
                ) {
                    overalDuration1 += ad.getDuration();
                };
                for (Advertisement ad: o2
                ) {
                    overalDuration2 += ad.getDuration();
                };
                return overalDuration2 - overalDuration1 ;
            }
            }
        ).thenComparing(new Comparator<List<Advertisement>>() {
            @Override
            public int compare(List<Advertisement> o1, List<Advertisement> o2) {
                return  o1.size() - o2.size();
            }
        }));
        List<Advertisement> finalList = lists.get(0);
        Collections.sort(finalList, new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return (int)(o2.getInitialAmount() - o1.getInitialAmount());
            }
        }.thenComparing(new Comparator<Advertisement>() {
            @Override
            public int compare(Advertisement o1, Advertisement o2) {
                return (int)(o2.getInitialAmount()/o2.getDuration() - o1.getInitialAmount()/o1.getDuration());
            }
        }));

        finalList.forEach(advertisement -> {
            System.out.println(advertisement);
            advertisement.revalidate();
        });


//Uncomment to test output
//        List<List<Advertisement>> listsWithoutDuplicates = lists.stream().distinct().collect(Collectors.toList());
//        listsWithoutDuplicates.forEach(new Consumer<List<Advertisement>>() {
//            int i = 0;
//            @Override
//            public void accept(List<Advertisement> list) {
//                Integer overalDuration = 0;
//                final AtomicReference<Integer> refOveralDuration = new AtomicReference<>(overalDuration);
//                Double overalProfit = 0.0;
//                final AtomicReference<Double> refOveralProfit = new AtomicReference<>(overalProfit);
//                list.forEach(new Consumer<Advertisement>() {
//                    @Override
//                    public void accept(Advertisement advertisement) {
//                        refOveralDuration.set(refOveralDuration.get() + Integer.valueOf(advertisement.getDuration()));
//                        refOveralProfit.set(refOveralProfit.get() + Double.valueOf(advertisement.getAmountPerOneDisplaying()));
//                    }
//                });
//                System.out.println(i++ + " " + refOveralProfit + " " + refOveralDuration + " " + list.size());
//            }
//        });

    }

    private boolean listFitsIntoLimit(List<Advertisement> list)
    {
        int totalTime = 0;
        if(list.size() > 0) {
            for (Advertisement advertisement : list) {
                totalTime += advertisement.getDuration();
            }
            return (totalTime <= timeSeconds);
        }
        else
            return false;
    }

    //метод заполняет статическую переменную AdvertisementManager.lists
    private void allCombinations(List<Advertisement> list)
    {
        if(listFitsIntoLimit(list))
            AdvertisementManager.lists.add(list);
        else
        {
//            for (int i = 0; i < list.size(); i++) {
//                List<Advertisement> newList = new LinkedList<>(list);
//                Collections.copy(newList, list);
//                newList.remove(list.get(i));
//                allCombinations(newList);
//            }
//            list.forEach(new Consumer<Advertisement>() {
//                @Override
//                public void accept(Advertisement advertisement) {
//                    List<Advertisement> newList = new LinkedList<>(list);
//                    Collections.copy(newList, list);
//                    newList.remove(advertisement);
//                    allCombinations(newList);
//                }
//            });
            list.forEach((Advertisement advertisement) ->
                {
                    List<Advertisement> newList = new LinkedList<>(list);
                    Collections.copy(newList, list);
                    newList.remove(advertisement);
                    allCombinations(newList);
                });
        }
    }

}
