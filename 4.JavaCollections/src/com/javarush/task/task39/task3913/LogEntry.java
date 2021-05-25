package com.javarush.task.task39.task3913;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LogEntry {
    private String ip;
    private String userName;
    private Date logDate;
    private Event event;
    private Integer taskNum;
    private Status status;

    public String getIp() {
        return ip;
    }

    public String getUserName() {
        return userName;
    }

    public Date getLogDate() {
        return logDate;
    }

    public Event getEvent() {
        return event;
    }

    public Integer getTaskNum() {
        return taskNum;
    }

    public Status getStatus() {
        return status;
    }

    public LogEntry(String s) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String[] oneLine = s.split("\t");
        Date parsedDate;
        parsedDate = format.parse(oneLine[2]);
        ip = oneLine[0];
        userName = oneLine[1];
        logDate = parsedDate;
        taskNum = null;

//        LOGIN - пользователь залогинился,
//        DOWNLOAD_PLUGIN - пользователь скачал плагин,
//        WRITE_MESSAGE - пользователь отправил сообщение,
//        SOLVE_TASK - пользователь попытался решить задачу,
//        DONE_TASK - пользователь решил задачу.

//                LOGIN,
//                DOWNLOAD_PLUGIN,
//                WRITE_MESSAGE,
//                SOLVE_TASK,
//                DONE_TASK

//            Для событий SOLVE_TASK и DONE_TASK существует дополнительный параметр,
//            который указывается через пробел, это номер задачи.
        if(oneLine[3].contains("SOLVE_TASK") || oneLine[3].contains("DONE_TASK"))
        {
            String[] task = oneLine[3].split(" ");
            taskNum = Integer.parseInt(task[1]);
            switch (task[0])
            {
                case "SOLVE_TASK": event = Event.SOLVE_TASK;
                break;

                case "DONE_TASK": event = Event.DONE_TASK;
                break;
            }
        }
        else {
            switch (oneLine[3]) {
                case "LOGIN":
                    event = Event.LOGIN;
                    break;

                case "DOWNLOAD_PLUGIN":
                    event = Event.DOWNLOAD_PLUGIN;
                    break;

                case "WRITE_MESSAGE":
                    event = Event.WRITE_MESSAGE;
                    break;
            }
        }

//        OK,
//        FAILED,
//        ERROR
        switch (oneLine[4])
        {
            case "OK": status = Status.OK;
            break;

            case "FAILED": status = Status.FAILED;
            break;

            case "ERROR": status = Status.ERROR;
            break;
        }

    }

}
