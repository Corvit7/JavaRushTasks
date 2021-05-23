package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* 
Древний Рим
*/

enum RomanNumbers{I, V, X, L, C, D, M}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    static Map<RomanNumbers, Integer> dict = new HashMap<>();

    static {
        dict.put(RomanNumbers.I, 1);
        dict.put(RomanNumbers.V, 5);
        dict.put(RomanNumbers.X, 10);
        dict.put(RomanNumbers.L, 50);
        dict.put(RomanNumbers.C, 100);
        dict.put(RomanNumbers.D, 500);
        dict.put(RomanNumbers.M, 1000);
    }

    public static int romanToInteger(String s) {
        int acc = 0;
        char[] symbols = s.toCharArray();
        RomanNumbers[] convertedToRomans = new RomanNumbers[symbols.length];

        for (int i = 0; i < symbols.length; i=i+1
             ) {
            switch (symbols[i])
            {
                case 'I': convertedToRomans[i] = RomanNumbers.I;
                break;
                case 'V': convertedToRomans[i] = RomanNumbers.V;
                break;
                case 'X': convertedToRomans[i] = RomanNumbers.X;
                break;
                case 'L': convertedToRomans[i] = RomanNumbers.L;
                break;
                case 'C': convertedToRomans[i] = RomanNumbers.C;
                break;
                case 'D': convertedToRomans[i] = RomanNumbers.D;
                break;
                case 'M': convertedToRomans[i] = RomanNumbers.M;
                break;
            }
        }

        Integer prev;
        Integer next;

        //MCMLXXXVIII
        for (int i = convertedToRomans.length; i > 1; i=i-2) {
            prev = dict.get(convertedToRomans[i-1]);
            next = dict.get(convertedToRomans[i-2]);
            if(prev <= next)
                acc += next + prev;
            else
                acc += (prev - next);
        }
        if (convertedToRomans.length%2==1)
            acc+= dict.get(convertedToRomans[0]);

        return acc;

    }
}
