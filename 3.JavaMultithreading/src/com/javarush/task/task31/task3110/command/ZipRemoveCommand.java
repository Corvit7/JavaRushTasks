package com.javarush.task.task31.task3110.command;

import com.javarush.task.task31.task3110.ConsoleHelper;
import com.javarush.task.task31.task3110.ZipFileManager;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ZipRemoveCommand extends ZipCommand {
    @Override
    public void execute() throws Exception {

        ZipFileManager zipFileManager = getZipFileManager();
        ConsoleHelper.writeMessage("Введите название файла, который хотите удалить");
        Path fileName = Paths.get(ConsoleHelper.readString());
//        Path fileName = Paths.get("G:\\TEST_DIR\\arch.zip\\IMG_20180816_085539.jpg");
        zipFileManager.removeFile(fileName);
    }

//    @Override
//    public ZipFileManager getZipFileManager() throws Exception {
//        Path zipPath = Paths.get("G:\\TEST_DIR\\arch.zip");
//        return new ZipFileManager(zipPath);
//    }
}
