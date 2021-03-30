package com.javarush.task.task25.task2515;

//В классе BaseObject необходимо:
//        а) Добавить переменные x (double), y (double), radius (double), геттеры и сеттеры для них.
//        б) Добавить логическую переменную isAlive (жив объект или уже нет).
//        в) Добавить геттер (isAlive()-метод для isAlive-переменной).
//        г) Добавить конструктор BaseObject(double x, double y, double radius).
//        д) Проследить, чтобы в конструкторе isAlive устанавливался в true (мертворожденные нам ни к чему).
//        Также надо пройтись по всем классам-наследникам и поправить у них конструкторы.
//        Если ты используешь Intellij IDEA - Alt+Insert тебе в помощь.
//


public abstract class BaseObject {
    private double x;
    private double y;
    private double radius;
    private boolean isAlive;

    public BaseObject(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        isAlive = true;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
