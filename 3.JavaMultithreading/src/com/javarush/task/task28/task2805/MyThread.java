package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {

    private static AtomicInteger priority = new AtomicInteger(0);

    private int generatePriority() {
        int l_int;
        int min = Thread.MIN_PRIORITY;
        int max = Thread.MAX_PRIORITY;

            l_int = priority.incrementAndGet();
            if (l_int > max)
            {
                l_int = min;
                priority.set(l_int);
            }
            if (l_int > Thread.currentThread().getThreadGroup().getMaxPriority())
            {
                l_int = min;
                priority.set(l_int);
            }
//        }
        return l_int;
    }

    public MyThread() {
        this.setPriority(generatePriority());
    }

    public MyThread(Runnable target) {
        super(target);
        this.setPriority(generatePriority());
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
        this.setPriority(generatePriority());
    }

    public MyThread(String name) {
        super(name);
        this.setPriority(generatePriority());
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
        this.setPriority(generatePriority());
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
        this.setPriority(generatePriority());
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
        this.setPriority(generatePriority());
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
        this.setPriority(generatePriority());
    }
}
