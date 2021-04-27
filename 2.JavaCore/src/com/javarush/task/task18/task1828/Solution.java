package com.javarush.task.task18.task1828;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/* 
Прайсы 2
*/

public class Solution {
    public static void main(String[] args) throws Exception {

        if(args.length == 0)
            return;
        if(!args[0].equals("-u") && !args[0].equals("-d"))
            throw new InternalError("неверный первый параметр. Допустимые значения -d, -u");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
        bufferedReader.close();
//        String fileName = "E:\\test\\Demo.txt";

        Path file = Paths.get(fileName);
        if(!Files.exists(file))
            Files.createFile(file);

        ArrayList<String> arrayList = new ArrayList<>();
        try( BufferedReader reader = new BufferedReader(new FileReader(fileName));
             BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,true))
        )
        {
            while (reader.ready())
                arrayList.add(reader.readLine());
            Iterator<String> iterator = arrayList.iterator();
            while (iterator.hasNext())
            {
                String s = iterator.next();
                if (Integer.parseInt(s.substring(0,8).trim()) == Integer.parseInt(args[1])) {
                    switch (args[0]) {
                        case "-d":
                            iterator.remove();
                            break;

                        case "-u":
                            arrayList.set(arrayList.indexOf(s), String.format("%-8s%-30s%-8s%-4s", args[1], args[2], args[3], args[4]));
                    }
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }



        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName,false))) {
            for (String s: arrayList
             ) {
                writer.write(s + "\n");
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}