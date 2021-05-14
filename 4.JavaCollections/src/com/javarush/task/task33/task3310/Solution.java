package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(), 10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new FileStorageStrategy(), 50);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        testStrategy(new HashBiMapStorageStrategy(), 10000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings)
    {
       Set<Long> resSet = new HashSet<>();
        for (String s: strings
             ) {
            resSet.add(shortener.getId(s));
        }
        return resSet;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> resSet = new HashSet<>();
        for (Long key: keys
             ) {
            resSet.add(shortener.getString(key));
        }
        return resSet;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber)
    {
        System.out.println(strategy.getClass().getSimpleName());
        Set<String> elements_inp = new HashSet<>();
        Set<String> elements_outp;
        Set<Long> keys;
        Date start, end;
        Long delta;
        boolean testPassed;
        for (int i = 0; i < elementsNumber; i++) {
            elements_inp.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);
        start = new Date();
        keys = getIds(shortener, elements_inp);
        end = new Date();
        delta = Math.abs(end.getTime()-start.getTime());
        System.out.println("getIds completed. Elapsed time:" + delta);
        start = new Date();
        elements_outp = getStrings(shortener, keys);
        end = new Date();
        delta = Math.abs(end.getTime() - start.getTime());
        System.out.println("getStrings completed. Elapsed time:" + delta);

        testPassed = true;
        for (String inp: elements_inp
             ) {
            if(!elements_outp.contains(inp))
            {
                testPassed = false;
                break;
            }
        }

        if(testPassed)
            System.out.println("Тест пройден.");
        else
            System.out.println("Тест не пройден.");


    }
}
