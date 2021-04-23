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

        byte k = 1;
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        BufferedInputStream reader = null;
        BufferedOutputStream writer = null;

        try {
            int i;
            fileInputStream = new FileInputStream(args[1]);
            fileOutputStream = new FileOutputStream(args[2]);
            reader = new BufferedInputStream(fileInputStream);
            writer = new BufferedOutputStream(fileOutputStream);
            byte[] bytes = new byte[0];
            while (reader.available() > 0) {
                bytes = new byte[reader.available()];
                reader.read(bytes);
            }
            switch (args[0]){
                case "-e":
                    byte[] ep = new byte[bytes.length];
                    for (i =0; i<ep.length; i++){
                        ep[i] = (byte) (bytes[i] + k);
                    } writer.write(ep);
                    break;
                case "-d":
                    byte[] dp = new byte[bytes.length];
                    for (i =0; i<dp.length; i++){
                        dp[i] = (byte) (bytes[i] - k);
                    } writer.write(dp);
                    break;
            }

        } catch (FileNotFoundException e) { } catch (IOException e) { }
        finally {
            try {
                reader.close();
                writer.flush();
                writer.close();
                fileInputStream.close();
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) { }
        }
    }

}
