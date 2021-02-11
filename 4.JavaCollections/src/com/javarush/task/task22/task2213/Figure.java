package com.javarush.task.task22.task2213;

public class Figure {
    private int x;
    private int y;
    private int[][] matrix;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public Figure(int x, int y, int[][] matrix) {
        this.x = x;
        this.y = y;
        this.matrix = matrix;
    }

//    Тетрис(16)
//    Напиши свою реализацию методов left(), right(), up(), down() в классе Figure.
//            Подумай, что должны делать эти методы?
//    Обрати внимание: в процессе реализации некоторых методов тебе надо будет проверять нарушение границ игрового поля, делать это нужно с помощью существующего метода isCurrentPositionAvailable().
//
//
//    Требования:
//            1. Метод left() должен уменьшать значение поля x на единицу, если это возможно(не нарушены границы игрового поля).
//            2. Метод right() должен увеличивать значение поля x на единицу, если это возможно(не нарушены границы игрового поля).
//            3. Метод up() должен уменьшать значение поля y на единицу.
//            4. Метод down() должен увеличивать значение поля y на единицу.

    public void left(){
        x--;
        if(!isCurrentPositionAvailable())
            x++;
    }
    public void right(){
        x++;
        if(!isCurrentPositionAvailable())
            x--;
    }
    public void down(){y++;}
    public void up(){y--;}
    public void rotate(){}
    public void downMaximum(){}
    public boolean isCurrentPositionAvailable(){return true; }
    public void landed(){}
}
