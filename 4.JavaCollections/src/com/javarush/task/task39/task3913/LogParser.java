package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.DateQuery;
import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.GenericDeclaration;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser implements IPQuery, UserQuery, DateQuery {
    private List<LogEntry> log = new ArrayList<>();

    public LogParser(Path logDir) {
        List<Path> fileList = new ArrayList<>();
        try(Stream<Path> paths = Files.walk(logDir)){
            fileList = paths.
                        filter((s) -> !Files.isDirectory(s)).
                        filter((s) -> s.toString().endsWith(".log")).
                        collect(Collectors.toList());
        } catch (IOException e) { e.printStackTrace(); }

        for (Path path: fileList
        ) {
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
            try {
                Stream<String> lines = Files.lines(path);
                lines.forEach(s ->{
                    try {
                        log.add(new LogEntry(s));
                    } catch (ParseException e) {e.printStackTrace();}
                });

            } catch (IOException e) {e.printStackTrace();}
        }

//        System.out.println("finish");
    }


    private boolean dateBetweenDates(Date current, Date after, Date before) {
        if (after == null) {
            after = new Date(0);
        }
        if (before == null) {
            before = new Date(Long.MAX_VALUE);
        }
        return current.after(after) && current.before(before);
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        TreeSet<String> uniqueIps = new TreeSet<>();
        for (LogEntry entry: log
             ) {
            if(dateBetweenDates(entry.getLogDate(), after, before))
                uniqueIps.add(entry.getIp());
        }
        return uniqueIps.size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        TreeSet<String> uniqueIps = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(dateBetweenDates(entry.getLogDate(), after, before))
                uniqueIps.add(entry.getIp());
        }
        return uniqueIps;
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        TreeSet<String> uniqueIps = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getUserName().equals(user))
                uniqueIps.add(entry.getIp());
        }
        return uniqueIps;
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        TreeSet<String> uniqueIps = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getEvent().equals(event))
                if(dateBetweenDates(entry.getLogDate(), after, before))
                    uniqueIps.add(entry.getIp());
        }
        return uniqueIps;
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        TreeSet<String> uniqueIps = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getStatus().equals(status))
                if(dateBetweenDates(entry.getLogDate(), after, before))
                    uniqueIps.add(entry.getIp());
        }
        return uniqueIps;
    }


    @Override
    public Set<String> getAllUsers() {
        TreeSet<String> users = new TreeSet<>();
        for (LogEntry entry: log
        ) {
             users.add(entry.getUserName());
        }
        return users;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        TreeSet<String> users = new TreeSet<>();
        for (LogEntry entry: log
        ) {
             if(dateBetweenDates(entry.getLogDate(), after, before))
                 users.add(entry.getUserName());
        }
        return users.size();
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        TreeSet<Event> events = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getUserName().equals(user))
                if(dateBetweenDates(entry.getLogDate(), after, before))
                    events.add(entry.getEvent());
        }
        return events.size();
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        TreeSet<String> users = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getIp().equals(ip))
                if(dateBetweenDates(entry.getLogDate(), after, before))
                    users.add(entry.getUserName());
        }
        return users;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        TreeSet<String> users = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getEvent().equals(Event.LOGIN))
                if(dateBetweenDates(entry.getLogDate(), after, before))
                    users.add(entry.getUserName());
        }
        return users;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        TreeSet<String> users = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getEvent().equals(Event.DOWNLOAD_PLUGIN))
                if(dateBetweenDates(entry.getLogDate(), after, before))
                    users.add(entry.getUserName());
        }
        return users;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        TreeSet<String> users = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getEvent().equals(Event.WRITE_MESSAGE))
                if(dateBetweenDates(entry.getLogDate(), after, before))
                    users.add(entry.getUserName());
        }
        return users;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        TreeSet<String> users = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getEvent().equals(Event.SOLVE_TASK))
                if(dateBetweenDates(entry.getLogDate(), after, before))
                    users.add(entry.getUserName());
        }
        return users;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        TreeSet<String> users = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getEvent().equals(Event.SOLVE_TASK))
                if(entry.getTaskNum().equals(task))
                    if(dateBetweenDates(entry.getLogDate(), after, before))
                        users.add(entry.getUserName());
        }
        return users;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        TreeSet<String> users = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getEvent().equals(Event.DONE_TASK))
                if(dateBetweenDates(entry.getLogDate(), after, before))
                    users.add(entry.getUserName());
        }
        return users;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        TreeSet<String> users = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getEvent().equals(Event.DONE_TASK))
                if(entry.getTaskNum().equals(task))
                    if(dateBetweenDates(entry.getLogDate(), after, before))
                        users.add(entry.getUserName());
        }
        return users;
    }

    @Override
    public Set<Date> getDatesForUserAndEvent(String user, Event event, Date after, Date before) {
        TreeSet<Date> dates = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getUserName().equals(user))
                if(entry.getEvent().equals(event))
                    if(dateBetweenDates(entry.getLogDate(), after, before))
                        dates.add(entry.getLogDate());
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenSomethingFailed(Date after, Date before) {
        TreeSet<Date> dates = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getStatus().equals(Status.FAILED))
                if(dateBetweenDates(entry.getLogDate(), after, before))
                    dates.add(entry.getLogDate());
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenErrorHappened(Date after, Date before) {
        TreeSet<Date> dates = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getStatus().equals(Status.ERROR))
                if(dateBetweenDates(entry.getLogDate(), after, before))
                    dates.add(entry.getLogDate());
        }
        return dates;
    }

    @Override
    public Date getDateWhenUserLoggedFirstTime(String user, Date after, Date before) {
        TreeSet<Date> dates = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getUserName().equals(user))
                if(entry.getEvent().equals(Event.LOGIN))
                    if(dateBetweenDates(entry.getLogDate(), after, before))
                        dates.add(entry.getLogDate());
        }
        if(dates.size()>0)
            return dates.first();
        else
            return null;
    }

    @Override
    public Date getDateWhenUserSolvedTask(String user, int task, Date after, Date before) {
        TreeSet<Date> dates = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getUserName().equals(user))
                if(entry.getEvent().equals(Event.SOLVE_TASK))
                    if(entry.getTaskNum().equals(task))
                        if(dateBetweenDates(entry.getLogDate(), after, before))
                            dates.add(entry.getLogDate());
        }
        if(dates.size()>0)
            return dates.first();
        else
            return null;
    }

    @Override
    public Date getDateWhenUserDoneTask(String user, int task, Date after, Date before) {
        TreeSet<Date> dates = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getUserName().equals(user))
                if(entry.getEvent().equals(Event.DONE_TASK))
                    if(entry.getTaskNum().equals(task))
                        if(dateBetweenDates(entry.getLogDate(), after, before))
                            dates.add(entry.getLogDate());
        }
        if (dates.size()>0)
            return dates.first();
        else
            return null;
    }

    @Override
    public Set<Date> getDatesWhenUserWroteMessage(String user, Date after, Date before) {
        TreeSet<Date> dates = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getUserName().equals(user))
                if(entry.getEvent().equals(Event.WRITE_MESSAGE))
                    if(dateBetweenDates(entry.getLogDate(), after, before))
                        dates.add(entry.getLogDate());
        }
        return dates;
    }

    @Override
    public Set<Date> getDatesWhenUserDownloadedPlugin(String user, Date after, Date before) {
        TreeSet<Date> dates = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getUserName().equals(user))
                if(entry.getEvent().equals(Event.DOWNLOAD_PLUGIN))
                    if(dateBetweenDates(entry.getLogDate(), after, before))
                        dates.add(entry.getLogDate());
        }
        return dates;
    }
}