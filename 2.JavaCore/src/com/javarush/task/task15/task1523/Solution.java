package com.javarush.task.task15.task1523;

/* 
Перегрузка конструкторов
*/

public class Solution {
    String name;
    int age;
    double height;

    public Solution(String name) {
        this.name = name;
    }

    protected Solution(int age) {
        this.age = age;
    }

    Solution(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private Solution(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public static void main(String[] args) {
    }
}