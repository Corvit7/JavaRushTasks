package com.javarush.task.task19.task1924;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Замена чисел
*/

public class Solution {
    public static Map<Integer, String> map = new HashMap<Integer, String>();

    static{
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
        String name = null;
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            name = reader.readLine();
        }
        try(BufferedReader file = new BufferedReader(new FileReader(name))){
            String line = null;
            while((line = file.readLine())!= null){
                String [] array  = line.trim().split("\\b");
                for(int i = 0; i < array.length; i++){
                    Pattern pattern = Pattern.compile("\\b\\d+\\b");
                    Matcher matcher = pattern.matcher(array[i]);
                    if (matcher.find()){
                        int c = Integer.parseInt(array[i]);
                        if(map.containsKey(c)){
                            array[i] = map.get(c);
                        }
                    }
                }
                String newLine = "";
                for(String x : array) newLine += x ;
                System.out.println(newLine);
            }
        }

    }
}