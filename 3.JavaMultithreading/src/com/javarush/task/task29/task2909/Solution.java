package com.javarush.task.task29.task2909;

import com.javarush.task.task29.task2909.human.Student;
import com.javarush.task.task29.task2909.human.University;

import java.util.LinkedList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        List<Student> students = new LinkedList<>();
        students.add(new Student("Anthony", 21, 5));
        students.add(new Student("Mark", 22, 3.8));
        students.add(new Student("Jack", 20, 4.0));


        University oxford = new University("Oxford", 750);
        oxford.setStudents(students);
//        System.out.println(oxford.getStudentWithAverageGrade(2).getName());
//        System.out.println(oxford.getStudentWithMaxAverageGrade().getName());
        System.out.println(oxford.getStudentWithMinAverageGrade().getName());
        oxford.expel(oxford.getStudentWithMinAverageGrade());
        System.out.println(oxford.getStudentWithMinAverageGrade().getName());
    }
}
