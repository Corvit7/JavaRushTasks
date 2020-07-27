package com.javarush.task.task27.task2705;

/* 
Второй вариант deadlock
*/
//Второй вариант deadlock
//        В методе secondMethod расставь synchronized блоки так, чтобы при использовании класса Solution нитями образовывался deadlock.
//
//
//        Требования:
//        1. В методе secondMethod должен присутствовать синхронизированный блок по объекту lock.
//        2. В методе secondMethod должен присутствовать вложенный синхронизированный блок по объекту this.
//        3. Поле lock должно быть приватным.
//        4. Метод secondMethod не должен быть объявлен с модификатором synchronized.

public class Solution {
    private final Object lock = new Object();

    public synchronized void firstMethod() {
        synchronized (lock) {
            doSomething();
        }
    }

    public void secondMethod() {
        synchronized (lock) {
            synchronized (this) {
                doSomething();
            }
        }
    }

    private void doSomething() {
    }

    public static void main(String[] args) {

        Solution solution1 = new Solution();
        Solution solution2 = new Solution();

        new Thread(new Runnable() {
            @Override
            public void run() {
                solution1.firstMethod();
                solution1.secondMethod();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                solution2.firstMethod();
                solution2.secondMethod();
            }
        }).start();


    }
}