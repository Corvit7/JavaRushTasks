package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.*;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogParser implements IPQuery, UserQuery, DateQuery, EventQuery, QLQuery {
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

    @Override
    public int getNumberOfAllEvents(Date after, Date before) {
        TreeSet<Event> events = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(dateBetweenDates(entry.getLogDate(), after, before))
                events.add(entry.getEvent());
        }
        return events.size();
    }

    @Override
    public Set<Event> getAllEvents(Date after, Date before) {
        TreeSet<Event> events = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(dateBetweenDates(entry.getLogDate(), after, before))
                events.add(entry.getEvent());
        }
        return events;
    }

    @Override
    public Set<Event> getEventsForIP(String ip, Date after, Date before) {
        TreeSet<Event> events = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getIp().equals(ip))
                if(dateBetweenDates(entry.getLogDate(), after, before))
                    events.add(entry.getEvent());
        }
        return events;
    }

    @Override
    public Set<Event> getEventsForUser(String user, Date after, Date before) {
        TreeSet<Event> events = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getUserName().equals(user))
                if(dateBetweenDates(entry.getLogDate(), after, before))
                    events.add(entry.getEvent());
        }
        return events;
    }

    @Override
    public Set<Event> getFailedEvents(Date after, Date before) {
        TreeSet<Event> events = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getStatus().equals(Status.FAILED))
                if(dateBetweenDates(entry.getLogDate(), after, before))
                    events.add(entry.getEvent());
        }
        return events;
    }

    @Override
    public Set<Event> getErrorEvents(Date after, Date before) {
        HashSet<Event> events = new HashSet<>();
        for (LogEntry entry: log
        ) {
            if(entry.getStatus().equals(Status.ERROR))
                if(dateBetweenDates(entry.getLogDate(), after, before))
                    events.add(entry.getEvent());
        }
        return events;
    }

    @Override
    public int getNumberOfAttemptToSolveTask(int task, Date after, Date before) {
        Map<Integer, Integer> map = getAllSolvedTasksAndTheirNumber(after, before);
        return map.getOrDefault(task, 0);
    }

    @Override
    public int getNumberOfSuccessfulAttemptToSolveTask(int task, Date after, Date before) {
        Map<Integer, Integer> map = getAllDoneTasksAndTheirNumber(after, before);
        return map.getOrDefault(task, 0);
    }

    @Override
    public Map<Integer, Integer> getAllSolvedTasksAndTheirNumber(Date after, Date before) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (LogEntry entry: log
             ) {
            if(dateBetweenDates(entry.getLogDate(), after, before))
                if(entry.getEvent().equals(Event.SOLVE_TASK))
                    if(map.containsKey(entry.getTaskNum()))
                        map.put(entry.getTaskNum(),map.get(entry.getTaskNum())+1);
                    else
                        map.put(entry.getTaskNum(), 1);
        }
        return map;
    }

    @Override
    public Map<Integer, Integer> getAllDoneTasksAndTheirNumber(Date after, Date before) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (LogEntry entry: log
        ) {
            if(dateBetweenDates(entry.getLogDate(), after, before))
                if(entry.getEvent().equals(Event.DONE_TASK))
                    if(map.containsKey(entry.getTaskNum()))
                        map.put(entry.getTaskNum(),map.get(entry.getTaskNum())+1);
                    else
                        map.put(entry.getTaskNum(), 1);
        }
        return map;
    }

    public Set<Date> getAllUniqueDates(Date after, Date before) {
        TreeSet<Date> dates = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(dateBetweenDates(entry.getLogDate(), after, before))
                dates.add(entry.getLogDate());
        }
        return dates;
    }

    public Set<Status> getAllUniqueStatuses(Date after, Date before) {
        TreeSet<Status> statuses = new TreeSet<>();
        for (LogEntry entry: log
        ) {
            if(dateBetweenDates(entry.getLogDate(), after, before))
                statuses.add(entry.getStatus());
        }
        return statuses;
    }

//    @Override
//    public Set<Object> execute(String query) {
//        String[] parts = query.split(" ");
//        Set<Object> result = new HashSet<>();
//        switch (parts[1])
//        {
//            case "ip": Collections.addAll(result, getUniqueIPs(null, null));
//            break;
//
//            case "user": Collections.addAll(result, getAllUsers());
//            break;
//
//            case "date": Collections.addAll(result, getAllUniqueDates(null, null));
//            break;
//
//            case "event": Collections.addAll(result, getAllEvents(null, null));
//            break;
//
//            case "status": Collections.addAll(result, getAllUniqueStatuses(null, null));
//            break;
//
//        }
//        return result;
//    }

    @Override
    public Set<Object> execute(String query) {
        String[] parts = query.split(" ");
        switch (parts[1])
        {
            case "ip": return new HashSet<>(getUniqueIPs(null, null));

            case "user":  return new HashSet<>(getAllUsers());

            case "date": return new HashSet<>(getAllUniqueDates(null, null));

            case "event": return new HashSet<>(getAllEvents(null, null));

            case "status": return new HashSet<>(getAllUniqueStatuses(null, null));
        }

        return null;
    }

}