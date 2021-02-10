package com.javarush.task.task32.task3213;

import java.io.IOException;
import java.io.StringReader;

/* 
Шифр Цезаря
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        StringReader reader = new StringReader("Khoor#Dpljr#&C,₷B'3");
//        StringReader reader = new StringReader("");
        System.out.println(decode(reader, -3));  //Hello Amigo #@)₴?$0
    }

    public static String decode(StringReader reader, int key) throws IOException {
        StringBuilder sB = new StringBuilder();
        int c;
        if (reader != null)
        {
            do{
                c = reader.read();
                if (c != -1)
                    sB.append((char)(c+key));
            } while (c != -1);
        }
        return sB.toString();
    }
}
