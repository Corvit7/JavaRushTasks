package com.javarush.task.task19.task1923;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/* 
Слова с цифрами
*/

public class Solution {
    public static void main(String[] args) throws IOException {
//        String file1 = "C:\\TestDir\\testFile5.txt";
//        String file2 = "C:\\TestDir\\resFile.txt";
        String file1 = args[0];
        String file2 = args[1];
        List<List<String>> words = new ArrayList<>();

        try(BufferedReader fileReader = new BufferedReader(new FileReader(file1)))
        {
            while (fileReader.ready())
                words.add(Arrays.asList(fileReader.readLine().split("\\s")));
        }

        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file2))){
            for (List<String> list: words
                 ) {
                for (String s: list
                ) {
                    byte[] bytes = s.getBytes();
                    for (byte b : bytes) {
                        if (b < 58 && b > 47) {
                            fileWriter.write(s);
                            fileWriter.write(" ");
                            break;
                        }
                    }

                }
            }
        }
    }
}
