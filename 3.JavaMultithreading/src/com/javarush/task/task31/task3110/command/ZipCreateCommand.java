package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.FileManager;
import com.javarush.task.task31.task3110.ZipFileManager;
import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipCreateCommand extends ZipCommand {

    @Override
    public void execute() throws Exception {
        try {
            ConsoleHelper.writeMessage("Создание архива.");
            ZipFileManager zipFileManager = getZipFileManager();

            ConsoleHelper.writeMessage("Введите имя файла или директории, которую хотите заархивировать.");
            String subject = ConsoleHelper.readString();
            Path subjectPath = Paths.get(subject);
            zipFileManager.createZip(subjectPath);
            ConsoleHelper.writeMessage("Архив создан.");
        } catch (PathIsNotFoundException e)
        {
            ConsoleHelper.writeMessage("Вы неверно указали имя файла или директории.");
        }
    }
}
