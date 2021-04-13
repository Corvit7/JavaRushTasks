package com.javarush.task.task15.task1529;

public class Plane implements CanFly{
    int numPassengers;

    @Override
    public void fly() {

    }

    public Plane(int numPassengers) {
        this.numPassengers = numPassengers;
    }
}
