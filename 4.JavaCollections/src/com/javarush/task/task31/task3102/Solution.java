package com.javarush.task.task31.task3102;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

import static java.nio.file.FileVisitResult.CONTINUE;

/* 
Находим все файлы
*/

//Находим все файлы
//        Реализовать логику метода getFileTree, который должен в директории root найти список всех файлов включая вложенные.
//        Используй очередь, рекурсию не используй.
//        Верни список всех путей к найденным файлам, путь к директориям возвращать не надо.
//        Путь должен быть абсолютный.
//
//
//        Требования:
//        1. Метод getFileTree должен принимать аргументом String root, по которому нужно найти все вложенные файлы.
//        2. Метод getFileTree должен возвращать список строк.
//        3. Нужно реализовать метод getFileTree: найти все файлы по указанному пути и добавить их в список.
//        4. Метод getFileTree должен быть вызван только 1 раз (рекурсию не использовать).

public class Solution {
    public static List<String> getFileTree(String root) throws IOException {
        List<String> list;
        PrintFiles printFiles = new PrintFiles();
        Files.walkFileTree(Paths.get(root), printFiles);
        list = new ArrayList<>(innerQueue);
        return list;

    }

    static Queue<String> innerQueue = new LinkedList<>();
    static class PrintFiles
            extends SimpleFileVisitor<Path> {

        // Print information about
        // each type of file.
        @Override
        public FileVisitResult visitFile(Path file,
                                         BasicFileAttributes attr) {
            innerQueue.add(file.toString());
            return CONTINUE;
        }

        // If there is some error accessing
        // the file, let the user know.
        // If you don't override this method
        // and an error occurs, an IOException
        // is thrown.
        @Override
        public FileVisitResult visitFileFailed(Path file,
                                               IOException exc) {
            System.err.println(exc);
            return CONTINUE;
        }
    }

    public static void main(String[] args) {

        String root = "D:\\Игорь\\2004г";
        try {
            for (String s: getFileTree(root)
            ) {
                System.out.println(s);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
