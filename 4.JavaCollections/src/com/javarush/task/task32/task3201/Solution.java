package com.javarush.task.task32.task3201;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/* 
Запись в существующий файл
*/

//Запись в существующий файл
//        В метод main приходят три параметра:
//        1) fileName - путь к файлу;
//        2) number - число, позиция в файле;
//        3) text - текст.
//        Записать text в файл fileName начиная с позиции number.
//        Запись должна производиться поверх старых данных, содержащихся в файле.
//        Если файл слишком короткий, то записать в конец файла.
//        Используй RandomAccessFile и его методы seek и write.
//
//
//        Требования:
//        1. В методе main класса Solution необходимо использовать RandomAccessFile.
//        2. В методе main класса Solution программа должна записывать данные в файл при помощи метода write класса RandomAccessFile.
//        3. Запись в файл должна происходить с указанной позиции с заменой содержимого.
//        4. Если файл слишком короткий, то запись text должна происходить в конец файла.

public class Solution {
    public static void main(String... args) {
        if(args.length != 3) {
            System.out.println("Wrong number of arguments");
            System.exit(1);
        }
        String fileName;
        int pos = 0;
        String text;

        fileName = args[0];
        try {
            pos = Integer.parseInt(args[1]);
        } catch (NumberFormatException e)
        {
            System.out.println("Неправильный тип данных. Ожидается целое число.");
            System.exit(1);
        }
        text = args[2];

        try {
            RandomAccessFile raf = new RandomAccessFile(fileName, "rw");
            if(raf.length() < pos) {
                //raf.setLength(raf.length() + text.length() + pos);
                //raf.setLength(pos + text.length());
                raf.seek(raf.length());
                raf.write(text.getBytes());
            }
            else {
                raf.seek(pos);
                raf.write(text.getBytes());
            }
        } catch (FileNotFoundException e)
        {
            System.out.println("Файл не найден");
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
