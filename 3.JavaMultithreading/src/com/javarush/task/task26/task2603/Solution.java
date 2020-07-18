package com.javarush.task.task26.task2603;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
Убежденному убеждать других не трудно
*/
//Убежденному убеждать других не трудно
//        В таблице есть колонки, по которым можно сортировать.
//        Пользователь имеет возможность настроить под себя список колонок, которые будут сортироваться.
//        Напиши public static компаратор CustomizedComparator, который будет:
//        1. В конструкторе принимать массив компараторов.
//        2. Сортировать данные в порядке, соответствующем последовательности компараторов.
//        Все переданные компараторы сортируют дженерик тип Т.
//        В конструктор передается как минимум один компаратор.
//
//
//        Требования:
//        1. Класс Solution должен содержать public static компаратор CustomizedComparator.
//        2. Класс CustomizedComparator должен содержать приватное поле comparators типа Comparator<T>[].
//        3. Класс CustomizedComparator должен содержать конструктор с параметром vararg компараторов.
//        4. Метод compare() класса CustomizedComparator должен сравнивать объекты в порядке, соответствующем последовательности компараторов comparators.
public class Solution {

    public static class CustomizedComparator<T> implements Comparator<T>{

        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator<T>... comparators) {
            this.comparators = comparators;
        }

        @Override
        public int compare(T o1, T o2)
        {
            int result = 0;
            for (Comparator comparator : comparators)
            {
                result = comparator.compare(o1, o2);
                if (result != 0) break; //если аргументы равны, переходим к следующему компаратору (критерию сравнения), если разные возвращаем результат сравнения
            }
            return result;
        }
    }

    public static void main(String[] args) {

        ArrayList<Woman> women = new ArrayList<Woman>();
        women.add(new Woman(18, 0, 45, 110, "Ann"));
        women.add(new Woman(21, 1, 57, 120, "Iren"));
        women.add(new Woman(5, 0, 60, 110, "Angelina"));

        Comparator<Woman> compareByHeight = new Comparator<Woman>() {

            public int compare(Woman o1, Woman o2) {
                return o2.height - o1.height;
            }
        };

        Comparator<Woman> compareByWeight = new Comparator<Woman>() {

            public int compare(Woman o1, Woman o2) {
                return o2.weight - o1.weight;
            }
        };

        CustomizedComparator<Woman> comparator = new CustomizedComparator<>(compareByHeight, compareByWeight);

        Collections.sort(women,comparator);

        for (int i = 0; i < women.size(); i++) {
            System.out.println(women.get(i).name);
        }
    }
}
