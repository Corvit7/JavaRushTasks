package com.javarush.task.task17.task1711;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* 
CRUD 2
*/

public class Solution {
    public static volatile List<Person> allPeople = new ArrayList<Person>();

    static {
        allPeople.add(Person.createMale("Иванов Иван", new Date()));  //сегодня родился    id=0
        allPeople.add(Person.createMale("Петров Петр", new Date()));  //сегодня родился    id=1
    }

    public static void main(String[] args) {
        //start here - начни тут

        args = new String[] {"-c", "Василий", "м", "15/04/1990", "Алена", "ж", "08/05/1996"};
//        args = new String[] {"-c", "Василий", "м", "15/04/1990", "Алена", "ж", "08/05/1996"};
//        args = new String[] {"-u", "0", "Александр", "м", "21/07/1987", "1", "Елена", "ж", "08/09/1993"};
//        args = new String[] {"-u", "1", "Елена", "ж", "08/09/1993"};
//        args = new String[] {"-d", "0", "1"};
//        args = new String[] {"-i", "0", "1"};
        Date date = null;
        int j = 0;
        if(!args[0].equals("-c") && !args[0].equals("-u") && !args[0].equals("-d") && !args[0].equals("-i") )
            throw new InternalError("не правильный первый параметр. Допустимы следующие: -c -u -d -i");
        switch(args[0]){
            case "-c":
                synchronized (allPeople)
                {
                    if((args.length - 1) % 3 != 0)
                        throw new InternalError("не правильное количество параметров");
                    else
                        for (int i = 1; i < args.length; i+=3) {
                            if(!args[i+1].equals("м") && !args[i+1].equals("ж"))
                                throw new InternalError("неправильный параметр. должно быть м или ж");
                            else {
                                try {
                                    date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[i + 2]);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                    throw new InternalError("некоорректный формат даты");
                                }
                                if (args[i + 1] == "м")
                                    allPeople.add(Person.createMale(args[i], date));
                                else
                                    allPeople.add(Person.createFemale(args[i], date));
                            }
                        }
                    for (Person person: allPeople
                    ) {
                        System.out.println(allPeople.indexOf(person));
                    }
                }
                break;
            case "-u":
                synchronized (allPeople) {
                    if((args.length - 1) % 4 != 0)
                        throw new InternalError("не правильное количество параметров");
                    else
                        for (int i = 1; i < args.length; i+=4) {
                            j = Integer.parseInt(args[i]);
                            if (!args[i + 2].equals("м") && !args[i + 2].equals("ж"))
                                throw new InternalError("неправильный параметр. должно быть м или ж");
                            else {
                                try {
                                    date = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(args[i + 3]);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                    throw new InternalError("некоорректный формат даты");
                                }
                                allPeople.get(j).setBirthDate(date);
                                allPeople.get(j).setName(args[i+1]);
                                if (args[i + 2] == "м")
                                    allPeople.get(j).setSex(Sex.MALE);
                                else
                                    allPeople.get(Integer.parseInt(args[i])).setSex(Sex.FEMALE);
                            }
                        }
                        }
                break;
            case "-i":
                synchronized (allPeople)
                {
                    for (int i = 1; i < args.length; i++) {
                        j = Integer.parseInt(args[i]);
                        System.out.println(allPeople.get(j).getName());
                        System.out.println(allPeople.get(j).getSex());
                        System.out.println(allPeople.get(j).getBirthDate());
                    }
                }
                break;
            case "-d":
                synchronized (allPeople)
                {
                    for (int i = 1; i < args.length; i++) {
                        j = Integer.parseInt(args[i]);
                        allPeople.get(j).setName(null);
                        allPeople.get(j).setSex(null);
                        allPeople.get(j).setBirthDate(null);
                    }
                }
            default:
                break;
        }
    }
}
