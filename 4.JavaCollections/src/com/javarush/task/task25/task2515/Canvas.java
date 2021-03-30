package com.javarush.task.task25.task2515;

//Требования:
//        1. В классе Canvas создай приватное int поле width. Добавь для него getter.
//        2. В классе Canvas создай приватное int поле height. Добавь для него getter.
//        3. В классе Canvas создай приватное поле matrix (char[][]). Добавь для него getter.
//        4. В классе Canvas создай конструктор Canvas(int width, int height). Инициализируй поля width и height.
//        5. Инициализируй в конструкторе поле matrix (char[height][width]).

public class Canvas {
    private int width;
    private int height;
    private char[][] matrix;

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
        matrix = new char[height][width];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char[][] getMatrix() {
        return matrix;
    }
}
