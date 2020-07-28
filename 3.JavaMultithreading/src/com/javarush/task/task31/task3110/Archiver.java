package com.javarush.task.task31.task3110;

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
            String sringPath = bufferedReader.readLine();
            Path path = Paths.get(sringPath);
            ZipFileManager zipFileManager= new ZipFileManager(path);
            System.out.println("Введите путь к архивируемому файлу:");
            sringPath = bufferedReader.readLine();
            path = Paths.get(sringPath);
            zipFileManager.createZip(path);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
