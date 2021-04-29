package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.TreeSet;

/* 
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        String fileName;
        fileName = args[0];
//        fileName = "C:\\TestDir\\testFile.txt";
        TreeSet<Character> treeSet = new TreeSet<>();
        int i = 1;
        int chr;

        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while (reader.ready())
            {
                chr = reader.read();
                if(Character.isAlphabetic(chr))
                    treeSet.add(Character.toLowerCase((char)chr));

            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        Iterator iterator = treeSet.iterator();
        while (iterator.hasNext())
        {
            System.out.print(iterator.next());
            i++;
            if(i > 5)
                break;

        }

    }
}
