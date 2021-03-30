package com.javarush.task.task25.task2515;


//Space (3)
//        Для чего нам нужен класс Space?
//        Чтобы хранить в себе все объекты и управлять их взаимодействием.
//        А какие параметры должны у него быть?
//        width (ширина), height (высота). Тип int.
//        А еще?
//        а) SpaceShip ship (космический корабль),
//        б) список для хранения всех НЛО - ufos (List<Ufo>),
//        в) список для хранения всех ракет - rockets (List<Rocket>),
//        г) список для хранения всех бомб - bombs (List<Bomb>).
//
//        Задание:
//        Добавь все эти поля в класс Space. Поля должны быть приватными.
//        Инициализируй коллекции.
//        И не забудь добавить полям getter'ы, а для ship еще и setter!
//
//        Ну и конструктор. А что должен содержать конструктор?
//        Достаточно будет width и height.
//
//
//        Требования:
//        1. В классе Space создай поле width. Добавь для него getter.
//        2. В классе Space создай поле height. Добавь для него getter.
//        3. В классе Space создай поле ship. Добавь для него getter и setter.
//        4. В классе Space создай поле ufos. Добавь для него getter.
//        5. В классе Space создай поле rockets. Добавь для него getter.
//        6. В классе Space создай поле bombs. Добавь для него getter.
//        7. В классе Space создай конструктор, который будет инициализировать width и height.
//        8. Инициализируй поля с коллекциями.

import java.util.LinkedList;
import java.util.List;

public class Space {

    private int width;
    private int height;
    private SpaceShip ship;
    private List<Ufo> ufos = new LinkedList<>();
    private List<Rocket> rockets = new LinkedList<>();
    private List<Bomb> bombs = new LinkedList<>();

    public Space(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void setShip(SpaceShip ship) {
        this.ship = ship;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public SpaceShip getShip() {
        return ship;
    }

    public List<Ufo> getUfos() {
        return ufos;
    }

    public List<Rocket> getRockets() {
        return rockets;
    }

    public List<Bomb> getBombs() {
        return bombs;
    }

    public static void main(String[] args) {

    }
}
