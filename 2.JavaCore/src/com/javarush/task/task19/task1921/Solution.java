package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        TreeMap <String,Float> map = new TreeMap<>();
        Pattern p1 = Pattern.compile("([а-яА-Яa-zA-Z- ]+)");
        Pattern p2 = Pattern.compile("(\\d+) (\\d+) (\\d+)");
        Matcher m;
        String line;
        String name;
        Person person;
        Calendar calendar;
        try(BufferedReader file = new BufferedReader(new FileReader(args[0])))
        {
            while (file.ready()) {
                line = file.readLine();
                m = p1.matcher(line);
                m.find();
                name = m.group(0).trim();
                m = p2.matcher(line);
                m.find();
                calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, Integer.parseInt(m.group(3)));
                calendar.set(Calendar.MONTH, Integer.parseInt(m.group(2)) -1);
                calendar.set(Calendar.DATE, Integer.parseInt(m.group(1)));
                person = new Person(name, calendar.getTime());
                PEOPLE.add(person);

            }
        }

        System.out.println("Finish");

    }
}
