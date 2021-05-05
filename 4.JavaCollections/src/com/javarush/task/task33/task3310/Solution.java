package com.javarush.task.task33.task3310;

import java.io.IOException;

public class Solution {
    public static void main(String[] args) {
        System.out.println(new Helper().generateRandomString());
        ExceptionHandler.log(new IOException("wrong"));

    }
}
