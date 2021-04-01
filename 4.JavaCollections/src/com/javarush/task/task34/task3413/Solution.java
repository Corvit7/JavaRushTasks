package com.javarush.task.task34.task3413;

/* 
Кеш на основании SoftReference
*/

public class Solution {
    public static void main(String[] args) {
        SoftCache cache = new SoftCache();

        for (long i = 0; i < 2_500_000; i++) {
            cache.put(i, new AnyObject(i, null, null));
        }
        System.out.println(cache.put(100L, new AnyObject(100L, "test", null)));
        System.out.println(cache.get(100L));
        System.out.println(cache.remove(100L));
        System.out.println(cache.get(100L));
        System.out.println(cache.remove(100L));
    }
}