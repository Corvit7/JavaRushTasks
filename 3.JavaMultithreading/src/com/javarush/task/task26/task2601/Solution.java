package com.javarush.task.task26.task2601;

/* 
Почитать в инете про медиану выборки
*/
//Почитать в инете про медиану выборки
//        Реализуй логику метода sort, который должен сортировать данные в массиве по удаленности от его медианы.
//        Верни отсортированный массив от минимального расстояния до максимального.
//        Если удаленность одинаковая у нескольких чисел, то сортируй их в порядке возрастания.
//
//        Пример входящего массива:
//        13, 8, 15, 5, 17
//        медиана - 13
//
//        Отсортированный масив:
//        13, 15, 17, 8, 5
//
//
//        Требования:
//        1. Программа не должна выводить текст в консоль.
//        2. Программа не должна считывать данные с консоли.
//        3. Класс Solution должен содержать публичный статический метод Integer[] sort(Integer[] array).
//        4. Метод sort(Integer[] array) класса Solution должен сортировать данные в массиве по удаленности от его медианы.

import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    public static void main(String[] args) {


        Integer[] arr = {13,8,15,5,17};
        Integer[] arr2 = {3,5,5,9,11};
        Integer[] arr3 = {1,2,3,4,5,6};
        Integer[] arr4 = {13,8,15,5};
        sort(arr4);

    }


    public static Integer[] sort(Integer[] array) {
        //implement logic here
        Arrays.sort(array);
        int median;

        if (array.length % 2 == 0)
            median = (array[(array.length / 2 - 1)] + array[(array.length / 2)]) / 2;
        else
            median = array[array.length/2];


        Comparator<Integer> compareByDistanceFromMedian = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Math.abs(median - o1) - Math.abs(median - o2);
            }
        };

        Arrays.sort(array, compareByDistanceFromMedian);

        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }

        return array;
    }
}
