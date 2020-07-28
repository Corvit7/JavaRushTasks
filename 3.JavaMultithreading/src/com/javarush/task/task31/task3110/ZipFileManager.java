package com.javarush.task.task31.task3110;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception
    {

        ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile));
        String fileName = source.getFileName().toString();
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOutputStream.putNextEntry(zipEntry);

//        FileOutputStream zipOutputStream = new FileOutputStream(zipFile.toString());


        try (InputStream inputStream = Files.newInputStream(source)) {
            byte[] buffer = new byte[250];
            int bufLength;
            while (inputStream.available() > 0)
            {
                bufLength = inputStream.read(buffer);
                zipOutputStream.write(buffer,0,bufLength);
            }
            zipOutputStream.finish();
            zipOutputStream.close();
        }
    }
}
