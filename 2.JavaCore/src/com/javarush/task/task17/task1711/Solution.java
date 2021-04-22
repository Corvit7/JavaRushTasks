package com.javarush.task.task17.task1711;

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

    public static void main(String[] args) throws ParseException {

        switch ( args[0]) {
            case "-c":
                synchronized  (allPeople){
                    for (int i = 1; i < args.length; i=i+3) {


                        if (args[i + 1].equals("м")) {
                            Person persono = Person.createMale(args[i], new Solution().birthDate(args[i+2]));
                            allPeople.add(persono);
                            System.out.println(allPeople.indexOf(persono));
                        }

                        if (args[i + 1].equals("ж")) {
                            Person persono = Person.createFemale(args[i], new Solution().birthDate(args[i+2]));
                            allPeople.add(persono);
                            System.out.println(allPeople.indexOf(persono));}
                    }
                }



                break;
            case "-u":
                synchronized (allPeople){
                    for (int i = 1; i <args.length ; i=i+4) {


                        allPeople.get(Integer.parseInt(args[i])).setBirthDate(new Solution().birthDate(args[i+3]));
                        if (args[i+2]=="ж"){
                            allPeople.get(Integer.parseInt(args[i])).setSex(Sex.FEMALE);}
                        if (args[i+2]=="м"){
                            allPeople.get(Integer.parseInt(args[i])).setSex(Sex.MALE);}

                        allPeople.get(Integer.parseInt(args[i])).setName(args[i+1]);}}
                break;


            case "-d":
                synchronized (allPeople){
                    for (int i = 1; i < args.length; i++) {
                        int eger = Integer.parseInt(args[i]);
                        allPeople.get(eger).setSex (null);
                        allPeople.get(eger).setName(null);
                        allPeople.get(eger).setBirthDate(null);}  }


                break;
            case "-i":
                synchronized (allPeople){
                    for (int i = 1; i < args.length; i++) {
                        System.out.print (allPeople.get(Integer.parseInt(args[i])).getName()+" ");
                        if (allPeople.get(Integer.parseInt(args[i])).getSex()== Sex.FEMALE)
                        {System.out.print ("ж"+" ");}
                        if (allPeople.get(Integer.parseInt(args[i])).getSex()== Sex.MALE)
                        {System.out.print ("м"+" ");}
                        System.out.println(  new Solution().birth(allPeople.get(Integer.parseInt(args[i])).getBirthDate()));}}
                break;

        }



    }

    public synchronized String birth (Date date) throws ParseException {
        SimpleDateFormat dataformat = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        return dataformat.format(date);}

    public synchronized Date birthDate (String date) throws ParseException {
        SimpleDateFormat dataformat = new SimpleDateFormat("dd/MM/yy");
        Date birthdate = dataformat.parse(date);
        return birthdate;}


}//start here - начни тут


