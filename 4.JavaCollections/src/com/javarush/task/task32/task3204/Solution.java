package com.javarush.task.task32.task3204;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

/* 
Генератор паролей
*/

public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {
        final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String lower = upper.toLowerCase(Locale.ROOT);
        final String digits = "0123456789";
        final String alphanum = upper + lower + digits;
        char[] symbols = alphanum.toCharArray();
        Random random = ThreadLocalRandom.current();
        boolean requirmentsMet = false;
        char[] buf = new char[8];

        ByteArrayOutputStream outp = new ByteArrayOutputStream();

        Pattern pattern1 = Pattern.compile ("[0-9]");
        Pattern pattern2 = Pattern.compile ("[A-Z]");
        Pattern pattern3 = Pattern.compile("[a-z]");

        do {
            for (int idx = 0; idx < buf.length; ++idx)
                buf[idx] = symbols[random.nextInt(symbols.length)];
            String result = new String(buf);
            if (pattern1.matcher(result).find()&&pattern2.matcher(result).find()&&pattern3.matcher(result).find()) {
                try {
                    outp.write(new String(buf).getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                requirmentsMet = true;
            }
        } while (!requirmentsMet);
        return outp;
    }
}
