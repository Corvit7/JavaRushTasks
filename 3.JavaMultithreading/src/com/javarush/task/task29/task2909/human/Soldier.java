package com.javarush.task.task29.task2909.human;

//import com.javarush.task.task25.task2511.Solution;

public class Soldier extends Human{
    public Soldier(String name, int age){ super(name, age);}


    public void fight() {
    }

    @Override
    public void live() {
        super.live();
        fight();
    }
}
