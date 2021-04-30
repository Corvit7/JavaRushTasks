package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {

        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        String filename;
        filename = consoleReader.readLine();
        consoleReader.close();
//        filename = "C:\\TestDir\\testFile4.txt";
        List<List<String>> lines = new ArrayList<>();
        int count;

        try(BufferedReader fileReader = new BufferedReader(new FileReader(filename))) {
            while(fileReader.ready())
                lines.add(Arrays.asList(fileReader.readLine().split("\\s")));
            for (List<String> list: lines
                 ) {
                count = 0;
                for (String s1: list
                     ) {
                    for (String s2: words
                         ) {
                        if(s1.equals(s2))
                            count++;
                    }
                }
                if(count==2)
                {
                    for (String s: list
                         ) {
                        System.out.print(s + " ");
                    }
                    System.out.println();
                }

            }
        }

    }
}
