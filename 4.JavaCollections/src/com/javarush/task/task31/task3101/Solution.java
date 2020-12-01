package com.javarush.task.task31.task3101;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

/* 
Проход по дереву файлов
*/

//Проход по дереву файлов
//        1. На вход метода main() подаются два параметра.
//        Первый - path - путь к директории, второй - resultFileAbsolutePath - имя (полный путь) существующего файла, который будет содержать результат.
//        2. Переименовать resultFileAbsolutePath в allFilesContent.txt (используй метод FileUtils.renameFile(), и, если понадобится, FileUtils.isExist()).
//        3. Для каждого файла в директории path и в ее всех вложенных поддиректориях выполнить следующее:
//        Если у файла длина в байтах НЕ больше 50, то для всех таких файлов:
//        3.1. Отсортировать их по имени файла в возрастающем порядке, путь не учитывать при сортировке.
//        3.2. В allFilesContent.txt последовательно записать содержимое всех файлов из п. 3.1. После каждого тела файла записать "\n".
//        Все файлы имеют расширение txt.
//        В качестве разделителя пути используй "/".
//        Для создания файлов используй конструктор File(String pathname).
//
//
//        Требования:
//        1. Файл, который приходит вторым параметром в main, должен быть переименован в allFilesContent.txt.
//        2. Нужно создать поток для записи в переименованный файл.
//        3. Содержимое всех файлов, размер которых не превышает 50 байт, должно быть записано в файл allFilesContent.txt в отсортированном по имени файла порядке.
//        4. Поток для записи в файл нужно закрыть.
//        5. Не используй статические переменные.

//public class Solution {
//    public static void main(String[] args) {
//
//        if(args.length != 2)
//        {
//            System.out.println("Неправильное число аргументов");
//        }
//        else
//        {
//            File path =  new File(args[0]);
//            File resultFileAbsolutePath = new File(args[1]);
//            List<File> fileList = new LinkedList<>();
//            for (File file: path.listFiles()
//                 ) {
//                if(file.length() < 50)
//                    fileList.add(file);
//            }
//            fileList.sort(new Comparator<File>() {
//                @Override
//                public int compare(File o1, File o2) {
////                    return o1.getName().compareTo(o2.getName());
//                    return o2.getName().compareTo(o1.getName());
//                }
//            });
//                if(resultFileAbsolutePath.isFile()) {
//                    File resultFileName = new File(resultFileAbsolutePath.getParent().concat("\\allFilesContent.txt"));
//                    FileUtils.renameFile(resultFileAbsolutePath, resultFileName);
//                    try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(resultFileName))) {
//                        fileList.forEach((file -> {
//                            try {
//                                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
//                                    String line;
//                                    while ((line = br.readLine()) != null) {
//                                        bufferedWriter.write(line);
//                                    }
//                                }
//                                bufferedWriter.write("\n");
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }));
//                    } catch (IOException exception) {
//                        exception.printStackTrace();
//                    }
//                }
//                else
//                    System.out.println("Результирующий файл не существует.");
//                }
//
//    }
//}


public class Solution {


    public static void main(String[] args) throws IOException {
        String path = args[0];
        File folder = new File(path);

        File resultFileAbsolutePath = new File(args[1]);

        File destination = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");

        FileUtils.renameFile(resultFileAbsolutePath, destination);
        OutputStream out = new FileOutputStream(destination);

        TreeMap<String, File> files = new TreeMap<>();
        files = collectFiles(files, folder);

        for (Map.Entry<String, File> entry : files.entrySet()) {
            FileInputStream fis = new FileInputStream(entry.getValue());
            while (fis.available() > 0) {
                out.write(fis.read());
            }
            out.write(10);
            fis.close();
        }

        out.close();

    }

    private static TreeMap<String, File> collectFiles(TreeMap<String, File> map, File folder) {
        for (File file : Objects.requireNonNull(folder.listFiles())) {
//            if (!FileUtils.isExist(file))
//                continue;

            if (file.isDirectory()) {
                map.putAll(collectFiles(map, file));
            } else {
                if (file.length() <= 50)
                    map.put(file.getName(), file);
                else
                    FileUtils.deleteFile(file);
            }
        }

        return map;
    }
}