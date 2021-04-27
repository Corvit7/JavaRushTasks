package com.javarush.task.task18.task1827;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* 
Прайсы
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        if(args.length == 0)
            return;
        if(args.length != 4)
            throw new InternalError("Неправильное число аргументов");
        if(!args[0].equals("-c"))
            throw new InternalError("неверный первый параметр. Допустимое значение -с");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        String fileName = "E:\\test\\Demo.txt";
        String fileName = bufferedReader.readLine();

        Path file = Paths.get(fileName);
        if(!Files.exists(file))
            Files.createFile(file);

        ArrayList<String> arrayList = new ArrayList<>();
        Set<Integer> IDsSet = new HashSet<>();
        Integer maxId =0;
        try( BufferedReader reader = new BufferedReader(new FileReader(fileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))
                )
        {
            while (reader.ready())
                arrayList.add(reader.readLine());
            for (String s: arrayList
                 ) {
                IDsSet.add(Integer.parseInt(s.substring(0,8).trim()));
            }
            for (Integer i: IDsSet
                 ) {
                if(maxId < i)
                    maxId = i;
            }
            maxId++;
            writer.write(String.format("\n%-8s%-30s%-8s%-4s", maxId, args[1], args[2], args[3]));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
