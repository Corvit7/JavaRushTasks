package com.javarush.task.task31.task3105;

import java.io.*;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        String zipFileName = args[1];
        File file = new File(fileName);

        Map<String, ByteArrayOutputStream> archivedFiles = new HashMap<>();
        try (ZipInputStream zipReader = new ZipInputStream(new FileInputStream(zipFileName))) {
            ZipEntry entry;
            while ((entry = zipReader.getNextEntry()) != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count = 0;
                while ((count = zipReader.read(buffer)) != -1)
                    byteArrayOutputStream.write(buffer, 0, count);

                archivedFiles.put(entry.getName(), byteArrayOutputStream);
            }
        }

        try (ZipOutputStream zipWriter = new ZipOutputStream(new FileOutputStream(zipFileName))) {
            for (Map.Entry<String, ByteArrayOutputStream> pair : archivedFiles.entrySet()) {
                if (pair.getKey().substring(pair.getKey().lastIndexOf("/") + 1).equals(file.getName())) continue;
                zipWriter.putNextEntry(new ZipEntry(pair.getKey()));
                zipWriter.write(pair.getValue().toByteArray());
            }

            ZipEntry zipEntry = new ZipEntry("new/" + file.getName());
            zipWriter.putNextEntry(zipEntry);
            Files.copy(file.toPath(), zipWriter);
        }
    }
}

//public class Solution {
//    public static void main(String[] args) throws IOException {
//
//        //Полный путь к файлу
//        String arg1 = args[0];
//        //путь к zip-архиву
//        String arg2 = args[1];
//
//        HashMap<String, ByteArrayOutputStream> tempStorage = new HashMap<>();
//
//        try(ZipInputStream zis = new ZipInputStream(new FileInputStream(arg2)))
//        {
//            byte[] buffer = new byte[8192];
//            int len;
//            ZipEntry entry;
//            while ( (entry = zis.getNextEntry()) != null)
//            {
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                while ((len = zis.read(buffer)) != -1) {
//                    baos.write(buffer, 0, len);
//                    baos.flush();
//                }
//                tempStorage.put(entry.getName(), baos);
//                baos.close();
//            }
//        }
//
//        Path root = Paths.get(arg2);
//        Files.delete(Paths.get(arg2));
////        Files.createFile(Paths.get(arg2));
//
//        FileOutputStream zop_file = new FileOutputStream(arg2);
//        ZipOutputStream zop = new ZipOutputStream(zop_file);
//        for(Map.Entry<String, ByteArrayOutputStream> entry: tempStorage.entrySet())
//        {
//            zop.putNextEntry(new ZipEntry(entry.getKey()));
//            File file = new File(entry.getKey());
//            FileOutputStream fos = new FileOutputStream(file);
//            fos.write(entry.getValue().toByteArray());
//            fos.close();
//            Files.copy(file.toPath(), zop);
//        }
//
//        File new_f = new File(arg1);
//        if(!tempStorage.containsKey(new_f.getName()))
//        {
//            zop.putNextEntry(new ZipEntry("new\\" + new_f.getName()));
//            Files.copy(new_f.toPath(), zop);
//        }
//
//        zop.close();
//
//        System.out.println("Done");
//
//    }
//}

//public class Solution {
//    public static void main(String[] args) throws IOException {
//
//        Map<ZipEntry, ByteArrayOutputStream> map = new HashMap<>();
//
//        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(args[1]))){
//            ZipEntry z;
//            while ((z = zipInputStream.getNextEntry())!=null){
//                int count;
//                byte [] buffer = new byte[1024];
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                while ((count = zipInputStream.read(buffer)) !=-1){
//                    baos.write(buffer,0,count);
//                }
//                map.put(z, baos);
//                zipInputStream.closeEntry();
//            }
//        } catch (Exception ex){
//            System.out.println(ex.toString());
//        }
//
//        try(ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(args[1]));) {
//            boolean hasFound = false;
//            String newFileName = Paths.get(args[0]).getFileName().toString();
//            for (Map.Entry<ZipEntry, ByteArrayOutputStream> entry: map.entrySet()) {
//                String curFileName = Paths.get(entry.getKey().getName()).toString();
//                if (!(Paths.get("new", newFileName).toString()).equals(curFileName)){
//                    zipOutputStream.putNextEntry(entry.getKey());
//                    zipOutputStream.write(entry.getValue().toByteArray());
//                    zipOutputStream.closeEntry();
//                } else {
//                    zipOutputStream.putNextEntry(new ZipEntry(Paths.get(entry.getKey().getName()).toString()));
//                    Files.copy(Paths.get(args[0]), zipOutputStream);
//                    zipOutputStream.closeEntry();
//                    hasFound = true;
//                }
//            }
//            if (!hasFound){
//                Path newFilePath = Paths.get("new", newFileName);
//                zipOutputStream.putNextEntry(new ZipEntry(newFilePath.toString()));
//                Files.copy(Paths.get(args[0]),zipOutputStream);
//                zipOutputStream.closeEntry();
//            }
//        }
//    }
//}
