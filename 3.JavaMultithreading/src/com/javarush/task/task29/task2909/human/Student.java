package com.javarush.task.task29.task2909.human;

import java.util.Date;

public class Student extends UniversityPerson {
//    private List<Human> children = new ArrayList<>();
    private double averageGrade;

    private Date beginningOfSession;
    private Date endOfSession;
    private int course;

    public Student(String name, int age, double averageGrade) {
        super(name, age);
        this.averageGrade = averageGrade;
    }




    public void live() {
        learn();
    }

    public void learn() {
    }



//    public void incAverageGradeBy01() {
//        averageGrade += 0.1;
//    }

//    public void incAverageGradeBy02() {
//        averageGrade += 0.2;
//    }

    public void incAverageGrade(double delta) {
        this.setAverageGrade(this.getAverageGrade() + delta);
    }

//    public void setValue(String name, double value) {
//        if (name.equals("averageGrade")) {
//            averageGrade = value;
//            return;
//        }
//        if (name.equals("course")) {
//            course = (int) value;
//            return;
//        }
//    }

    public void setCourse(int course)
    {
        this.course = course;
    }

    public void setAverageGrade(double grade)
    {
        this.averageGrade = grade;
    }

//    public void setBeginningOfSession(int day, int month, int year) {
//        beginningOfSession = new Date(year, month, day);
//    }

    public void setBeginningOfSession(Date date){
        beginningOfSession = date;
    }

//    public void setEndOfSession(int day, int month, int year) {
//        endOfSession = new Date(year, month, day);
//    }

    public void setEndOfSession(Date date){
        endOfSession = date;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public int getCourse() {
        return course;
    }

    @Override
    public String getPosition() {
        return "Студент";
    }
}