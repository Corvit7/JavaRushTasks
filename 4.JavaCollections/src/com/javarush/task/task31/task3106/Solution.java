package com.javarush.task.task31.task3106;

/*
Разархивируем файл
*/

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Solution {
    public static void main(String[] args) {
        Vector<FileInputStream> files = new Vector<>();
        Arrays.sort(args, 1, args.length);

        try {
            for(int i = 1; i < args.length; i++) {
                files.addElement(new FileInputStream(args[i]));
            }
        } catch (FileNotFoundException exc) {}

        try (ZipInputStream zip = new ZipInputStream(new SequenceInputStream(files.elements()));) {
            byte[] buffer = new byte[2048];
            int len;
//            int cur_pos;
            ZipEntry entry;
            Files.createDirectories(Paths.get(args[0]));
            while ((entry = zip.getNextEntry()) != null) {
                try(FileOutputStream fos = new FileOutputStream(args[0]+"\\" + entry.getName())) {
                while ((len = zip.read(buffer)) != -1) {
                        fos.write(buffer, 0, len);
                        fos.flush();
                    }
                }
                zip.closeEntry();
            }
        } catch (IOException exc) {exc.printStackTrace();}
    }
}