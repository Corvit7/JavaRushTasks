package com.javarush.task.task29.task2909.human;


//Задания:
//        1.1. Подъем поля. Подними поле children в базовый класс.
//        1.2. Подъем метода. Подними сеттер и геттер для children в базовый класс.
//        1.3. Инкапсуляция коллекции.
//        1.3.1. Метод getChildren должен возвращать не модифицируемое представление списка children.
//        1.3.2. Убери сеттер для children.
//        1.3.3. Добавь методы addChild(Human) и removeChild(Human). Реализуй их логику.
//
//
//        Требования:
//        1. Поле children должно быть расположено в классе Human, и не должно быть расположено в классах Teacher и Student.
//        2. Сеттер и геттер для поля children должны быть расположены в классе Human, и не должны быть расположены в классах Teacher и Student.
//        3. Метод getChildren в классе Human должен возвращать Collections.unmodifiableList(children).
//        4. Необходимо удалить метод setChildren из класса Human.
//        5. Необходимо добавить методы addChild (Human) и removeChild (Human) в класс Human, и реализовать их.

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Human implements Alive{
    private static int nextId = 0;
    private int id;
    protected int age;
    protected String name;

    protected int[] size;


    public static final int FIRST = 1;
    public static final int SECOND = 2;
    public static final int THIRD = 3;
    public static final int FOURTH = 4;
    private int bloodGroup;

    private List<Human> children = new ArrayList<>();
//    private void setChildren(List<Human> children) {
//        this.children = children;
//    }
    public     List<Human>  getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void addChild(Human child) {
        children.add(child);
    }

    public void removeChild(Human child){
        children.remove(child);
    }

    public void setBloodGroup(int code) {
        bloodGroup = code;
    }

    public int getBloodGroup() {
        return bloodGroup;
    }

    public Human( String name, int age
//            boolean isSoldier
    ) {
//        this.isSoldier = isSoldier;
        this.name = name;
        this.age = age;
        this.id = nextId;
        nextId++;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void live() {
//        if (isSoldier)
//            fight();
    }

//    public void fight() {
//    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

    public void printSize() {
        System.out.println("Рост: " + size[0] + " Вес: " + size[1]);
    }

    public String getPosition() {
        return "Человек";
    }

    public void printData() {
        System.out.println(getPosition() + ": " + getName());
    }
}