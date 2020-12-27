package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        //Полный путь к файлу
        String arg1;
        //путь к zip-архиву
        String arg2;

        arg1 = "E:\\фото\\test_file.txt";
        arg2 = "E:\\фото\\test_arch.zip";

        arg1 = args[1];
        arg2 = args[2];

        HashMap<String, ByteArrayOutputStream> tempStorage = new HashMap<>();

        try(ZipInputStream zis = new ZipInputStream(new FileInputStream(arg2)))
        {
            byte[] buffer = new byte[8192];
            int len;
            ZipEntry entry;
            while ( (entry = zis.getNextEntry()) != null)
            {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while ((len = zis.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                    baos.flush();
                }
                tempStorage.put(entry.getName(), baos);
                baos.close();
            }
        }

        Path root = Paths.get(arg2);
        Files.delete(Paths.get(arg2));
        Files.createFile(Paths.get(arg2));

        FileOutputStream zop_file = new FileOutputStream(arg2);
        ZipOutputStream zop = new ZipOutputStream(zop_file);
        for(Map.Entry<String, ByteArrayOutputStream> entry: tempStorage.entrySet())
        {
            zop.putNextEntry(new ZipEntry(entry.getKey()));
            File file = new File(entry.getKey());
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(entry.getValue().toByteArray());
            fos.close();
            Files.copy(file.toPath(), zop);
        }

        File new_f = new File(arg1);
        if(!tempStorage.containsKey(new_f.getName()))
        {
            zop.putNextEntry(new ZipEntry("new\\" + new_f.getName()));
            Files.copy(new_f.toPath(), zop);
        }

        zop.close();

        System.out.println("Done");

    }
}
