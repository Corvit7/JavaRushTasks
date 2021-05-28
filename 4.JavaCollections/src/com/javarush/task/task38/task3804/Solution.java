package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

public class Solution {
    public static Class getFactoryClass() {
        return ExceptionFactory.class;
    }

    public static void main(String[] args) {
        Throwable throwable = ExceptionFactory.createException(ApplicationExceptionMessage.SOCKET_IS_CLOSED);
        System.out.println(throwable.toString());
        Throwable throwable2 = ExceptionFactory.createException(ApplicationExceptionMessage.UNHANDLED_EXCEPTION);
        System.out.println(throwable2.toString());
        Throwable throwable3 = ExceptionFactory.createException(UserExceptionMessage.USER_DOES_NOT_EXIST);
        System.out.println(throwable3);
    }
}