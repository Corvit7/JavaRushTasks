package com.javarush.task.task31.task3110;

import com.javarush.task.task31.task3110.exception.PathIsNotFoundException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;



//Задания на сегодня:
//        1. Реализуй приватный метод void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception в классе ZipFileManager.
//
//        Он должен:
//        1.1. Создавать InputStream, для файла с именем fileName, расположенным в директории filePath
//        1.2. Создавать новый элемент архива ZipEntry, в качестве имени используй fileName, преобразовав его в String
//        1.3. Копировать данные из InputStream (из п.1.1) в переданный zipOutputStream
//        1.4. Закрывать элемент архива
//        1.5. Закрывать InputStream, сделай это с помощью try-with-resources
//        2. Замени часть кода метода createZip вызовом нового метода addNewZipEntry.
//        Передай значение source.getParent() в параметр filePath, а source.getFileName() в filename.
//        3. Реализуй приватный метод void copyData(InputStream in, OutputStream out) throws Exception.
//        Он должен читать данные из in и записывать в out, пока не вычитает все.
//        4. Замени часть кода метода addNewZipEntry на вызов метода copyData
//        5. Вернемся к createZip:
//        5.1. В начале метода проверь, что существует директория (zipFile.getParent()), в которой мы будем создавать zipFile, если ее нет, то создай ее.
//        5.2. Если source является обычным файлом (для проверки используй Files.isRegularFile), то оставим просто вызов addNewZipEntry
//        5.3. Если source является директорией (для проверки используй Files.isDirectory), то:
//        5.3.1. Создай объект класса файловый менеджер FileManager, в конструктор передадим source
//        5.3.2. Получи список файлов у файлового менеджера, сохраним его в переменную fileNames
//        5.3.3. Для всех элементов fileNames, вызови метод addNewZipEntry(zipOutputStream, source, fileName)
//        5.4. Если source не является ни папкой, ни файлом, то кинь исключение PathIsNotFoundException.

public class ZipFileManager {
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception
    {
        //проверка существования директории, где создаем zip-архив
        if(Files.notExists(zipFile.getParent()))
            Files.createDirectory(zipFile.getParent());

        ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile));
//        String fileName = source.getFileName().toString();
//        ZipEntry zipEntry = new ZipEntry(fileName);
//        zipOutputStream.putNextEntry(zipEntry);

//        FileOutputStream zipOutputStream = new FileOutputStream(zipFile.toString());

        if(Files.isRegularFile(source))
            addNewZipEntry(zipOutputStream, source.getParent(), source.getFileName());
//        else if (Files.isDirectory(source))
//        {
//            FileManager fileManager = new FileManager(source);
//            List<Path> fileNames = fileManager.getFileList();
//            Iterator<Path> iterator = fileNames.iterator();
//            while(iterator.hasNext()) {
//                Path current = iterator.next();
////                addNewZipEntry(zipOutputStream, current.getParent().relativize(fileManager.getRootPath()), current.getFileName());
//                addNewZipEntry(zipOutputStream, current.getParent(), current.getFileName());
//            }
//        }
//        else throw new PathIsNotFoundException();
        else if (Files.isDirectory(source)) {
            FileManager fileManager = new FileManager(source);
            List<Path> fileNames = fileManager.getFileList();
            for (Path entry : fileNames) {
                addNewZipEntry(zipOutputStream, entry.getParent(), entry.getFileName());
            }
        } else throw new PathIsNotFoundException();

        zipOutputStream.close();

//        try (InputStream inputStream = Files.newInputStream(source)) {
//            byte[] buffer = new byte[250];
//            int bufLength;
//            while (inputStream.available() > 0)
//            {
//                bufLength = inputStream.read(buffer);
//                zipOutputStream.write(buffer,0,bufLength);
//            }
//            zipOutputStream.finish();
//            zipOutputStream.close();
//        }
        
    }

    private void addNewZipEntry(ZipOutputStream zipOutputStream, Path filePath, Path fileName) throws Exception
    {
        Path fullPath = filePath.resolve(fileName);

        ZipEntry zipEntry = new ZipEntry(fileName.toString());
        zipOutputStream.putNextEntry(zipEntry);
        try(InputStream is =  Files.newInputStream(fullPath)) {
//            byte[] buffer = new byte[250];
//            int bufLength;
//            while (is.available() > 0)
//            {
//                bufLength = is.read(buffer);
//                zipOutputStream.write(buffer,0,bufLength);
//            }
            copyData(is, zipOutputStream);
            zipOutputStream.finish();
//            zipOutputStream.close();
        }
    }

    private void copyData(InputStream in, OutputStream out) throws Exception
    {
        byte[] buffer = new byte[250];
        int bufLength;
        while (in.available() > 0)
        {
            bufLength = in.read(buffer);
            out.write(buffer,0,bufLength);
        }

    }

//  для тестов
    public static void main(String[] args) throws Exception {
        ZipFileManager zipFileManager = new ZipFileManager(Paths.get("G:\\TEST_DIR\\test_arch.zip"));
        zipFileManager.createZip(Paths.get("G:\\TEST_DIR\\фото"));
//        FileManager fileManager = new FileManager(Paths.get("G:\\TEST_DIR\\фото"));
//        fileManager.getFileList();
//        List<Path> fileNames = fileManager.getFileList();
//        Iterator<Path> iterator = fileNames.iterator();
//        while(iterator.hasNext()) {
//            Path current = iterator.next();
//            System.out.println(current.getFileName());;
//        }
    }

}
