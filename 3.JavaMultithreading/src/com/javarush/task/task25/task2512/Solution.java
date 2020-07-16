package com.javarush.task.task25.task2512;

/* 
Живем своим умом
*/

//Живем своим умом
//        В классе Solution реализуй интерфейс UncaughtExceptionHandler, который должен:
//        1. прервать нить, которая бросила исключение.
//        2. вывести в консоль стек исключений, начиная с самого вложенного.
//
//        Пример исключения:
//        new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")))
//
//        Пример вывода:
//        java.lang.IllegalAccessException: GHI
//        java.lang.RuntimeException: DEF
//        java.lang.Exception: ABC
//
//
//        Требования:
//        1. Класс Solution должен реализовывать интерфейс Thread.UncaughtExceptionHandler.
//        2. После вызова uncaughtException нужно прервать нить, которая бросила исключение.
//        3. Затем, вывести в консоль стек исключений, начиная с самого вложенного исключения.
//        4. Сообщения должны выводиться в формате "exception class: exception message".

public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if(e == null) return;
        t.interrupt();
        uncaughtException(t, e.getCause());
        System.out.println(e.getClass().getName() + ": " + e.getLocalizedMessage());
    }

    public static void main(String[] args) {
//        Thread thread = new Thread();
//        thread.setUncaughtExceptionHandler(new Solution());
//        thread.start();
        new Solution().uncaughtException(new Thread(), new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI"))));
    }
}
