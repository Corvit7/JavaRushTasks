package com.javarush.task.task27.task2701;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Избавляемся от меток
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String string = reader.readLine();
        String substring = reader.readLine();

        if (isSubstringPresent(substring, string)) {
            System.out.println("String: \n" + substring + "\nis present in the string: \n" + string);
        } else {
            System.out.println("String: \n" + substring + "\nis not present in the string: \n" + string);
        }
    }

    static boolean isSubstringPresent(String substring, String string) {
//        boolean found = true;
//        found = string.contains(substring);
//        return found;
//        int max = string.length() - substring.length();
//        label:
//        for (int i = 0; i <= max; i++) {
//            int length = substring.length();
//            int j = i;
//            int k = 0;
//            while (length-- != 0) {
//                if (string.charAt(j++) != substring.charAt(k++)) {
//                    found = false;
//                    continue label;
//                }
//            }
//            found = true;
//            break label;
//        }

        for (int i = 0; i <= string.length()-substring.length(); i++)
            for (int j = i; string.charAt(j)==substring.charAt(j-i) ; j++)
                if (j==i+substring.length()-1) return true;
        return false;

    }
}

