package com.javarush.task.task25.task2504;

/* 
Switch для нитей
*/

//Switch для нитей
//        Обработай список нитей в зависимости от состояния:
//        1. Если нить еще не запущена, то запусти ее.
//        2. Если нить в ожидании, то прерви ее.
//        3. Если нить работает, то проверь маркер isInterrupted.
//        4. Если нить прекратила работу, то выведи в консоль ее приоритет.
//        Используй switch.
//
//
//        Требования:
//        1. Метод processThreads принимает аргументом массив нитей.
//        2. Если переданная нить не запущена, нужно ее запустить.
//        3. Если переданная нить находится в ожидании, нужно ее прервать.
//        4. Если переданная нить работает, то нужно проверить маркер isInterrupted.
//        5. Если переданная нить завершила работу, нужно вывести в консоль ее приоритет.
//        6. Метод processThreads должен использовать оператор switch.


import com.sun.org.apache.bcel.internal.generic.NOP;

public class Solution {
    public static void processThreads(Thread... threads) {
        //implement this method - реализуйте этот метод
        for (Thread thread:threads
             ) {
            switch (thread.getState())
            {
                case NEW: thread.start();
                break;
                case BLOCKED:
                case TIMED_WAITING:
                case WAITING:
                    thread.interrupt();
                break;
                case RUNNABLE:
                    if(thread.isInterrupted())
                        System.out.println("NOP");
                break;
                case TERMINATED:
                    System.out.println(thread.getPriority());
            }
        }
    }

    public static void main(String[] args) {
    }
}
