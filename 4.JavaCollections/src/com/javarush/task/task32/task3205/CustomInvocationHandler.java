package com.javarush.task.task32.task3205;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class CustomInvocationHandler implements InvocationHandler {
    private SomeInterfaceWithMethods loc_someInterfaceWithMethods;

    public CustomInvocationHandler(SomeInterfaceWithMethods someInterfaceWithMethods) {
        loc_someInterfaceWithMethods = someInterfaceWithMethods;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(String.format("%s in", method.getName()));
        Object obj = method.invoke(loc_someInterfaceWithMethods, args);
        System.out.println(String.format("%s out", method.getName()));
        return obj;
    }
}
