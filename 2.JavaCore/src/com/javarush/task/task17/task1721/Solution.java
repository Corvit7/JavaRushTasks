package com.javarush.task.task17.task1721;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* 
Транзакционность
*/

public class Solution {
    public static List<String> allLines = new ArrayList<String>();
    public static List<String> forRemoveLines = new ArrayList<String>();

    public static void main(String[] args) throws CorruptedDataException, IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Path file1;
        Path file2;
        file1 = Paths.get("D:\\Хранилище\\testDir\\file1.txt");
        file2 = Paths.get("D:\\Хранилище\\testDir\\file2.txt");
        try {
            file1 = Paths.get(bufferedReader.readLine());
            file2 = Paths.get(bufferedReader.readLine());
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        allLines.addAll(readFileToList(file1));
        forRemoveLines.addAll(readFileToList(file2));

        Solution solution = new Solution();
        solution.joinData();
    }

    public void joinData() throws CorruptedDataException {

        if(!allLines.containsAll(forRemoveLines)) {
            allLines.clear();
            throw new CorruptedDataException();
        }
        allLines.removeAll(forRemoveLines);
        System.out.println("finish");
    }

    private static List<String> readFileToList(Path path) throws IOException{
        List<String> list = new ArrayList<>();
        try (BufferedReader br = Files.newBufferedReader(path)) {
            String line;
            while ((line = br.readLine()) != null){
                list.add(line);
            }
        } catch (IOException e)
        {
            throw e;
        }
        return list;
    }
}
