package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread{
    private Thread target;
    private State newState;
    private State currentState;


    @Override
    public void run() {
//        super.run();
//        boolean isRun = true;
//        while (isRun)
//        {
//            try {
//                Thread.sleep(500);
//                if(target.getState().equals(State.TERMINATED))
//                    isRun = false;
//            } catch (InterruptedException e)
//            {
//                e.printStackTrace();
//            }
//        }

//        System.out.println(this.getState());
//        while (target.getState() != State.TERMINATED) {
//            if (!(target.getState() == newState))
//                System.out.println(target.getState());
//            newState = target.getState();
//        }
//        System.out.println(target.getState());
//        this.interrupt();

//        System.out.println(target.getState());
        while (true) {
            currentState = target.getState();

            if (currentState != newState) {
                newState = target.getState();
                System.out.println(newState);
            }
            if (currentState.equals(State.TERMINATED)) {
                break;
            }
        }
        interrupt();
    }

    public LoggingStateThread(Thread target) {
        this.target = target;
//        System.out.println(this.getState());

    }
}
