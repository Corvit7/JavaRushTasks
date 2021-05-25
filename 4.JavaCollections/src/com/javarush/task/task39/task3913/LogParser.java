package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;

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

public class LogParser implements IPQuery {
    private List<LogEntry> log = new ArrayList<>();

    public LogParser(Path logDir) {
        List<Path> fileList = new ArrayList<>();
        try(Stream<Path> paths = Files.walk(logDir)){
            fileList = paths.
                        filter((s) -> !Files.isDirectory(s)).
                        filter((s) -> s.toString().endsWith(".log")).
                        collect(Collectors.toList());
        } catch (IOException e) { e.printStackTrace(); }

//                lines.forEach(new Consumer<String>() {
//                    @Override
//                    public void accept(String s) {
//                        oneLine = Arrays.asList(s.split("\t"));
//                        try {
//                            log.add(new LogEntry(oneLine.get(0),
//                                    oneLine.get(1),
//                                    format.parse(oneLine.get(2)),
//                                    oneLine.get(3),
//                                    oneLine.get(4)));
//                        } catch (ParseException ex ) { ex.printStackTrace();}
//                    }
//                });


//        for (Path path: fileList
//             ) {
//            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
//            try {
//                Stream<String> lines = Files.lines(path);
//                lines.forEach(s ->{
//                    String[] oneLine = s.split("\t");
//                    Date logDate;
//                    try {
//                        logDate = format.parse(oneLine[2]);
//                        log.add(new LogEntry(oneLine[0],
//                                oneLine[1],
//                                logDate,
//                                oneLine[3],
//                                oneLine[4]));
//                    } catch (ParseException e) {e.printStackTrace();}
//                });

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


//    1.2.1. Метод getNumberOfUniqueIPs(Date after, Date before) должен возвращать количество уникальных IP адресов за выбранный период.
//    Здесь и далее, если в методе есть параметры Date after и Date before, то нужно возвратить данные касающиеся только данного периода (включая даты after и before).
//    Если параметр after равен null, то нужно обработать все записи, у которых дата меньше или равна before.
//    Если параметр before равен null, то нужно обработать все записи, у которых дата больше или равна after.
//    Если и after, и before равны null, то нужно обработать абсолютно все записи (без фильтрации по дате).

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
}