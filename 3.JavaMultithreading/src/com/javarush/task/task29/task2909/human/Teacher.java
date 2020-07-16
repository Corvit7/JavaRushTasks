package com.javarush.task.task29.task2909.human;

public class Teacher extends UniversityPerson {

    private int numberOfStudents;

    private int course;

    public Teacher(String name, int age, int numberOfStudents) {
        super(name, age);
        this.name = name;
        this.age = age;
        this.numberOfStudents = numberOfStudents;
    }



    public void live() {
        teach();
    }

    public void teach() {
    }

    public int getCourse() {
        return course;
    }

    @Override
    public String getPosition() {
        return "Преподаватель";
    }
}