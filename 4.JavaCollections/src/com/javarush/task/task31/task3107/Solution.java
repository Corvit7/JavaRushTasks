package com.javarush.task.task31.task3107;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Null Object Pattern
*/

public class Solution {
    private FileData fileData;

    public Solution(String pathToFile) {

            try {
                Path thisFile = Paths.get(pathToFile);
                Boolean isDirectory = Files.isDirectory(thisFile);
                Boolean isExecutable = Files.isExecutable(thisFile);
                Boolean isHidden = Files.isHidden(thisFile);
                Boolean isWritable = Files.isWritable(thisFile);

                fileData = new ConcreteFileData(isHidden, isExecutable, isDirectory, isWritable);

            } catch (IOException e)
            {
                fileData = new NullFileData(e);
            }
    }

    public FileData getFileData() {
        return fileData;
    }
}
