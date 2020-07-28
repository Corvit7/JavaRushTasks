package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.command.ExitCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Archiver {
    public static void main(String[] args)  {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите путь к архиву:");
        try{
            String stringPath;
            Path path;
            stringPath = bufferedReader.readLine();
            path= Paths.get(stringPath);
//            path = Paths.get("E:\\фото\\test_arch");
            ZipFileManager zipFileManager= new ZipFileManager(path);
            System.out.println("Введите путь к архивируемому файлу:");
            stringPath = bufferedReader.readLine();
            path = Paths.get(stringPath);
//            path = Paths.get("E:\\фото\\test_file.txt");
            zipFileManager.createZip(path);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        ExitCommand exitCommand = new ExitCommand();
        try
        {
            exitCommand.execute();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
