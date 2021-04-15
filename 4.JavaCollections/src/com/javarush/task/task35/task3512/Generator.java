package com.javarush.task.task35.task3512;

public class  Generator<T> {

    private Class<T> classVar;

    public Generator(Class<T> inpClass)
    {
        classVar = inpClass;
    }

    T newInstance() {
        T outp = null;
        try {
            outp = classVar.newInstance();
        } catch (InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }
        return outp;
    }
}
