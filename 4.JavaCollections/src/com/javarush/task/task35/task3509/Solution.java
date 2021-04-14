package com.javarush.task.task35.task3509;

import java.util.*;

/* 
Collections & Generics
*/

public class Solution {

    public static void main(String[] args) {
        ArrayList<String> arraylist = newArrayList("1","2","3");
        for (Object ob: arraylist
             ) {
            System.out.println(ob);
        }

        HashMap<Integer, Object> map = newHashMap(newArrayList(1,2,3), newArrayList(1,2,3));

        for(Map.Entry<Integer, Object> entry: map.entrySet())
        {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }

    }

    public static <T> ArrayList<T> newArrayList (T... elements) {
        //напишите тут ваш код
        return new ArrayList<>(Arrays.asList(elements));
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        //напишите тут ваш код
        return new HashSet<>(Arrays.asList(elements));
    }

    public static <K, V> HashMap <K,V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        //напишите тут ваш код
        if(!(keys.size() == values.size()))
            throw new IllegalArgumentException();
        HashMap<K,V> res = new HashMap<>();
        for (int i = 0; i < keys.size(); i++) {
            res.put(keys.get(i), values.get(i));
        }
        return res;
    }
}
