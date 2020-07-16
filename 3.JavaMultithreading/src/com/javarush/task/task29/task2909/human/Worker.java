package com.javarush.task.task29.task2909.human;

public class Worker extends Human{
    private double salary;
    public String company;

    public Worker(String name, int age) {
        super(name, age);
    }

    //    public Worker(String name, int age) {
//        human = new Human(name, age);
//    }

//    public void live() {
//        live();
//    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}