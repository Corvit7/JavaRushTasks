package com.javarush.task.task25.task2508;

public class TaskManipulator implements Runnable, CustomThreadManipulator {

//    Thread th = new Thread();
//    String threadName;
//
//    public TaskManipulator(String threadName) {
//        this.threadName = threadName; }
//    public TaskManipulator(){}
//
//    public void run() {
//        while (!th.isInterrupted()) {
//            System.out.println(threadName);
//            try {
//                th.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    @Override
//    public void start(String threadName)  {
//        (th = new Thread(new TaskManipulator(threadName))).start();
//        // (new TaskManipulator()).start(threadName);
//    }
//    @Override
//    public void stop() {
//        th.interrupt();
//    }

    Thread lastThread = new Thread(this);
    String threadName;

    public Thread getThreadByName(String threadName) {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals(threadName)) return t;
        }
        return null;
    }


    public TaskManipulator(String threadName) {
        this.threadName = threadName; }
    public TaskManipulator(){}

    @Override
    public void run() {
        while (!lastThread.isInterrupted()) {
            System.out.println(threadName);
            try {
                lastThread.sleep(100);
            } catch (InterruptedException e) {
//                e.printStackTrace();
                break;
            }
        }
    }

    @Override
//    public void start(String threadName) {
//        lastThread = new Thread();
//        lastThread.setName(threadName);
//        lastThread.start();
//    }
    public void start(String threadName)  {
//        (lastThread = new Thread(new TaskManipulator(threadName))).start();
        lastThread = new Thread(new TaskManipulator(threadName));
        lastThread.setName(threadName);
        lastThread.start();
//        lastThread.start();
        // (new TaskManipulator()).start(threadName);
    }

    @Override
    public void stop() {
        this.lastThread.interrupt();
    }
}
