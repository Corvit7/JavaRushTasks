package com.javarush.task.task39.task3913;

import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Solution {
    public static void main(String[] args) {
//        LogParser logParser = new LogParser(Paths.get("c:/logs/"));
//        System.out.println(logParser.getNumberOfUniqueIPs(null, new Date()));

        LogParser logParser = new LogParser(Paths.get("C:\\repos\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task39\\task3913\\logs"));
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            System.out.println(logParser.getIPsForEvent(Event.LOGIN, null, format.parse("25.05.2022")));
            System.out.println(logParser.getIPsForStatus(Status.OK, null, null));
            System.out.println(logParser.getIPsForUser("Amigo", null, null));
            System.out.println(logParser.getNumberOfUniqueIPs(null, null));
            System.out.println(logParser.getUniqueIPs(null, null));
            System.out.println(logParser.getLoggedUsers(null, null));
            System.out.println(logParser.getDateWhenUserLoggedFirstTime("Dum-dum", null, null));
            System.out.println(logParser.getNumberOfAttemptToSolveTask(7, null, null));
            System.out.println(logParser.getNumberOfSuccessfulAttemptToSolveTask(7, null, null));
            System.out.println(logParser.execute("get ip"));
            System.out.println(logParser.execute("get ip for status = \"FAILED\""));
            System.out.println(logParser.execute("get ip for user = \"Amigo\""));
            System.out.println(logParser.execute("get ip for event = \"LOGIN\""));
            System.out.println(logParser.execute("get ip for date = \"05.01.2029 20:22:55\""));
        } catch (ParseException e) {e.printStackTrace();}
    }
}