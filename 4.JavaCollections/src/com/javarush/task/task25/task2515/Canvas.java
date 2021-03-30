package com.javarush.task.task25.task2515;

//Space (8)
//        Что мы будем делать с Canvas?
//        Мы будем рисовать на нем (в его матрице).
//
//        Поэтому нам понадобятся два метода:
//public void setPoint(double x, double y, char c),
//public void drawMatrix(double x, double y, int[][] matrix, char c).
//
//        Первый метод - setPoint будет "ставить точку в координатах x,y цветом c".
//        В методе нужно:
//        а) математически округлить x и y до целых чисел,
//        б) занести в matrix[y][x] значение с, если x и y находятся в пределах матрицы (0<=x<matrix[0].length и 0<=y<matrix.length).
//
//        Второй метод - drawMatrix копирует переданную ему картинку (матрицу) в матрицу Canvas.
//        И не просто копирует, а начиная с координат x, y.
//
//        В методе нужно:
//        а) с помощью двух вложенных циклов пройтись по всем ячейкам переданной картинки,
//        б) если значение ячейки matrix[i][j] не равно 0, то покрасить в матрице объекта Canvas точку (x+j, y+i) в цвет c:
//        setPoint(x+j, y+i, c)
//
//
//        Требования:
//        1. В классе Canvas создай метод public void setPoint(double x, double y, char c).
//        2. Метод setPoint должен заносить в матрицу по координатам x, y символ с.
//        3. В классе Canvas создай метод public void drawMatrix(double x, double y, int[][] matrix, char c).
//        4. Метод drawMatrix, начиная с координат x, y, должен заполнять полотно символами с, согласно переданной в метод матрицей matrix.

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

    public void setPoint(double x, double y, char c)
    {
        int loc_x = (int)Math.round(x);
        int loc_y = (int)Math.round(y);
        if(loc_x >= 0 && loc_x < matrix[0].length && loc_y >= 0 && loc_y < matrix.length)
            matrix[loc_y][loc_x] = c;
    }

//    public void setPoint(double x, double y, char c){
//        if (x<0 || y <0 || y > this.matrix.length || x>= this.matrix[0].length) {
//            return;
//        }
//        this.matrix[(int)Math.round(y)][(int)Math.round(x)] = c;
//    }

    public void drawMatrix(double x, double y, int[][] matrix, char c){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] != 0)
                    setPoint(x+j, y+i, c);
            }
        }

    }

    public void clear() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                setPoint(j, i, ' ');
            }
        }
    }

    public void print() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[j][i]);
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }

    public static void main(String[] args) {
        Canvas canvas = new Canvas(3,3);
        int[][] matrix = new int[3][3];
        matrix[0][0] = 127;
        matrix[1][0] = 127;
        matrix[2][0] = 127;
        canvas.drawMatrix(0,0, matrix, 'b');
        canvas.print();
        canvas.clear();
        canvas.print();
    }

}
