package com.javarush.task.task31.task3112;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
*/

public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
//        // implement this method
//        URL url = new URL(urlString);
//        InputStream inputStream = url.openStream();
//
//        Path tempFile = Files.createTempFile("temp-",".tmp");
//        Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
//        Path pathResult = downloadDirectory.resolve(Paths.get(url.getPath()).toFile().getName());
//        Files.createFile(pathResult);
//        Files.move(tempFile, pathResult, StandardCopyOption.REPLACE_EXISTING);
//        Files.deleteIfExists(tempFile);
//        return pathResult;

        URL url = new URL(urlString);
        Path fileName = Paths.get(url.getPath()).getFileName();
        Path fileDir = Paths.get(String.valueOf(downloadDirectory));
        InputStream inputStream = url.openStream();
        Path tempFile = Files.createTempFile("temp-", ".txt");
        Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);
        String separator = "/";
        Path newName = Paths.get(fileDir.toString() + separator +fileName.toString());
        downloadDirectory = Files.move(tempFile, newName, StandardCopyOption.REPLACE_EXISTING);
        return downloadDirectory;
    }
}
