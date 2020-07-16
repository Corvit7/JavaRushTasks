package com.javarush.task.task29.task2909.human;

//4.1. Замена наследования делегированием.
//        4.1.1. Класс University не должен наследоваться от Student.
//        4.1.2. Класс University должен содержать список students. Не забудь его инициализировать.
//        4.1.3. Добавь сеттер и геттер для students.
//        4.1.4. Университет имеет название (name) и возраст (age). Добавь необходимые поля, сеттеры и геттеры для них.
//        4.2. Извлечение суперкласса.
//        4.2.1. Создай класс UniversityPerson в пакете human.
//        4.2.2. Перенеси в него поле university.
//        4.2.3. Перенеси сеттер и геттер для поля university.
//        4.2.4. Унаследуй необходимые классы от UniversityPerson.
//        4.3. Замена простого поля объектом. Измени тип поля university на University.
//


import java.util.LinkedList;
import java.util.List;

public class University {


    private String name;
    private int age;
    private List<Student> students = new LinkedList<>();

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade() {
        //TODO:
        return null;
    }

    public Student getStudentWithMaxAverageGrade(double averageGrade) {
        //TODO:
        return null;
    }

    public void getStudentWithMinAverageGradeAndExpel() {
        //TODO:
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}