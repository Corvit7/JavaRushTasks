package com.javarush.task.task36.task3605;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.TreeSet;

/* 
Использование TreeSet
*/

public class Solution {
    public static void main(String[] args) throws IOException {

//        String fileName;
//        fileName = args[0];
////        fileName = "C:\\TestDir\\testFile.txt";
//        TreeSet<Character> treeSet = new TreeSet<>();
//        int i = 1;
//        int chr;
//
//        try(BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
//            while (reader.ready())
//            {
//                chr = reader.read();
//                if(Character.isAlphabetic(chr))
//                    treeSet.add(Character.toLowerCase((char)chr));
//
//            }
//        } catch (IOException e)
//        {
//            e.printStackTrace();
//        }
//
//        Iterator iterator = treeSet.iterator();
//        while (iterator.hasNext())
//        {
//            System.out.print(iterator.next());
//            i++;
//            if(i > 5)
//                break;
//
//        }

        TreeSet<Character> result = new TreeSet<>();
        new String(Files.readAllBytes(Paths.get(args[0])))
                .toLowerCase().chars()
                .mapToObj(c-> (char)c).filter(Character::isLetter)
                .forEach(result::add);
        result.stream().limit(5).forEach(System.out::print);

    }
}
