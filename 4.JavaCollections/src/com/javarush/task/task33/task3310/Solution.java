package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.HashMapStorageStrategy;
import com.javarush.task.task33.task3310.strategy.StorageStrategy;

import java.io.IOException;
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10000; i++) {
            map.put(i, String.valueOf(i));
        }

        System.out.println(map.size());
//        System.out.println(map.);
//        map.put("0", "zero");
//        System.out.println(map.get("0").hashCode());
//        System.out.println(hashCode("0".toCharArray()));
//        System.out.println(tableSizeFor(9));
//        testStrategy(new HashMapStorageStrategy(), 10000);
//        System.out.println(HashMap.class.hashCode());
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

//    static final int hash(Object key) {
//        int h;
//        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
//    }
    static int hash(int h)
    {
        int res;
        res = (h >>> 20) ^ (h >>> 12);
        return res;
    }
    static int hashCode(char[] value) {
        int hash = 0;
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];
            }
            hash = h;
        }
        return h;
    }

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
