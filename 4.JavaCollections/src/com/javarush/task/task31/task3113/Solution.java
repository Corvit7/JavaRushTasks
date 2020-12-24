package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import static java.nio.file.FileVisitResult.CONTINUE;

/* 
Что внутри папки?
*/

public class Solution {

    static int folder_count = 0;
    static int files_count = 0;
    static double size = 0;

    static class Statistic extends SimpleFileVisitor<Path> {

        @Override
        public FileVisitResult visitFile(Path file,
                                         BasicFileAttributes attr) {
                files_count++;
                try {
                    size += Files.size(file);
                } catch (IOException e)
                {
                    e.printStackTrace();
                }

            return CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            if(!(dir.getParent() == dir))
                folder_count++;
            return super.preVisitDirectory(dir, attrs);
        }


    }

    public static void main(String[] args) throws IOException {
        String root = "D:\\Игорь\\";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        root = bufferedReader.readLine();
        if (!Files.isDirectory(Paths.get(root)))
        {
            System.out.println(root + " - не папка");
        }
        else {
            Statistic statistic = new Statistic();
            Files.walkFileTree(Paths.get(root), statistic);
            System.out.println("Всего папок - " + --folder_count);
            System.out.println("Всего файлов - " + files_count);
            System.out.println("Общий размер - " + size);
        }
    }
}
