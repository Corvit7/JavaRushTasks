package com.javarush.task.task28.task2811;

import java.util.LinkedHashMap;

/* 
ReentrantReadWriteLock
*/

//ReentrantReadWriteLock
//        Класс ReadWriteMap должен предоставлять корректный доступ к объекту Map из многих потоков, но в его реализации были допущены ошибки. Найди их и исправь.
//
//        P.S. Для корректной работы Map в многопоточной среде, желательно пользоваться стандартной реализацией, например ConcurrentHashMap из пакета java.util.concurrent. Но иногда требуется работа с другим типом мапы, например LinkedHashMap и тогда предложенное решение будет как никогда кстати.
//
//
//        Требования:
//        1. Поле readLock должно быть инициализировано с помощью метода readLock вызванного на объекте lock.
//        2. Поле writeLock должно быть инициализировано с помощью метода writeLock вызванного на объекте lock.
//        3. В методе put должен быть вызван метод lock на объекте writeLock.
//        4. В методе get должен быть вызван метод lock на объекте readLock.
//        5. В методе put в блоке finally должен быть вызван метод unlock на объекте writeLock.
//        6. В методе get в блоке finally должен быть вызван метод unlock на объекте readLock.

public class Solution {
    public static void main(String[] args) {
        ReadWriteMap<Integer, String> linkedSafeMap = new ReadWriteMap<>(new LinkedHashMap<>());

//        testing
//        new Thread(){
//            @Override
//            public void run() {
//                for (int i = 0; i < 10; i++)
//                {
//                    linkedSafeMap.put(i, "test" + i);
//                    try{
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
//
//        new Thread(){
//            @Override
//            public void run() {
//                try
//                {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e)
//                {
//                    e.printStackTrace();
//                }
//                for (int i = 10; i < 20; i++)
//                {
//                    linkedSafeMap.put(i, "test" + i);
//                    try{
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();
//
//        new Thread()
//        {
//            @Override
//            public void run() {
//                for (int j = 0; j < 20; j++) {
//                    for (int i = 0; i < 20; i++) {
//                        System.out.println(linkedSafeMap.get(i));
//                    }
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e)
//                    {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }.start();

    }
}
