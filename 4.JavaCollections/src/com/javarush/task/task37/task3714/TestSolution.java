package com.javarush.task.task37.task3714;

import org.junit.Test;
import org.junit.Assert;

public class TestSolution {
    @Test
    public void testEquals() {
        Object[][] testCases = {{"IV", 4}, {"VI", 6}, {"VIII", 8}, {"IX", 9}, {"XV", 15}, {"XL", 40},
                {"XLII", 42}, {"LX", 60}, {"LXXX", 80}, {"LXXXIII", 83}, {"XCIV", 94}, {"XC", 90},
                {"CL", 150}, {"CCLXXXIII", 283}, {"DCCC", 800}, {"MCMLXXXVIII", 1988}, {"MMDCLXXXIII", 2683},
                {"MMDDCCLLXXVVII", 3332}, {"MMMD", 3500}};
        for (Object[] tc : testCases) Assert.assertEquals(Solution.romanToInteger((String) tc[0]), (int) tc[1]);
    }
}