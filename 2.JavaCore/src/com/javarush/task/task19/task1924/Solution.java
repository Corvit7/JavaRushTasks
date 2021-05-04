package com.javarush.task.task19.task1924;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* 
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        map.put(0, "ноль");
        map.put(1, "один");
        map.put(2, "два");
        map.put(3, "три");
        map.put(4, "четыре");
        map.put(5, "пять");
        map.put(6, "шесть");
        map.put(7, "семь");
        map.put(8, "восемь");
        map.put(9, "девять");
        map.put(10, "десять");
        map.put(11, "одиннадцать");
        map.put(12, "двенадцать");
    }

    public static void main(String[] args) throws IOException{

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName;
//        fileName = consoleReader.readLine();
//        consoleReader.close();
        fileName = "C:\\repos\\JavaRushTasks\\tests\\task19.task1924.txt";

        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> words_res = new ArrayList<>();

        try(BufferedReader file = new BufferedReader(new FileReader(fileName)))
        {
            while (file.ready())
            {
                words.addAll(Arrays.asList(file.readLine().split("\\s", -1)));
                words.add("\n");
            }
        }

        for (String s : words
             ) {
            try {
                if (map.get(Integer.parseInt(s)) != null)
                    words_res.add(map.get(Integer.parseInt(s)));
                else
                    words_res.add(s);
            } catch (NumberFormatException e)
            {
                words_res.add(s);
            }
        }

        for (String s: words_res
             ) {
            if(!s.equals("\n"))
                System.out.print(s + " ");
            else
                System.out.print(s);
        }

    }
}
