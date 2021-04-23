package com.javarush.task.task18.task1826;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Шифровка
*/

public class Solution {
    public static void main(String[] args) throws IOException {

        try(BufferedReader br = Files.newBufferedReader(Paths.get(args[1])))
        {
            try(BufferedWriter br2 = Files.newBufferedWriter(Paths.get(args[2])))
            {
                int val;
                while ((val = br.read()) != -1)
                {
                        br2.write(encrypt(val, args[0]));
                }
            } catch (IOException e)
            {
                throw e;
            }
        } catch (IOException e)
        {
            throw e;
        }

    }

    public static int encrypt(int chr, String mode)
    {
        if (mode.equals("-e"))
            return chr + 9;
        else if (mode.equals("-d"))
            return chr - 9;
        else return chr;
    }

}
