package com.javarush.task.task19.task1919;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Считаем зарплаты
*/

public class Solution {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        TreeMap <String,Float> map = new TreeMap<>();
        Pattern p = Pattern.compile("([а-яА-Яa-zA-Z]+)([\\ ]*)([+-]?[0-9]*[.,]?[0-9]+)");
        Matcher m;
        String line;
        String name;
        Float acc;
        try(BufferedReader file = new BufferedReader(new FileReader(args[0])))
        {
            while (file.ready()) {
                line = file.readLine();
                m = p.matcher(line);
                m.find();
                if (map.containsKey(m.group(1))) {
                    acc = map.get(m.group(1));
                    acc += Float.parseFloat(m.group(3));
                    map.replace(m.group(1), acc);
                }
                else
                    map.put(m.group(1), Float.parseFloat(m.group(3)));
            }
        }

        for (Map.Entry<String, Float> entry: map.entrySet()
             ) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
