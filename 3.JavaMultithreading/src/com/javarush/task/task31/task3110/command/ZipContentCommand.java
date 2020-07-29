package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.FileProperties;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.util.Iterator;

public class ZipContentCommand extends ZipCommand{
    @Override
    public void execute() throws Exception {
        ConsoleHelper.writeMessage("Содержимое архива");
        ZipFileManager zipFileManager = getZipFileManager();

        Iterator<FileProperties> iterator = zipFileManager.getFilesList().iterator();
        while(iterator.hasNext())
            System.out.println(iterator.next().getName());
    }
}
