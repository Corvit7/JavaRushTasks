package com.javarush.task.task37.task3710.decorators;

import com.javarush.task.task37.task3710.shapes.Shape;


//Decorator
//        Создай класс RedShapeDecorator в пакете decorators. Он должен расширять функциональность объектов типа Shape не меняя их структуру.
//
//        Я уже создал абстрактный класс ShapeDecorator, поэтому:
//        1) Класс RedShapeDecorator сделай наследником класса ShapeDecorator.
//        2) Реализуй приватный метод setBorderColor() с одним параметром типа Shape.
//        Он должен выводить на экран фразу "Setting the border color for XXX to red.", где XXX - имя конкретного декорируемого класса (можешь воспользоваться методами getClass().getSimpleName() вызванными на объекте полученном в качестве параметра).
//        3) Переопредели метод draw(), в нем сначала измени цвет отображаемого объекта с помощью метода setBorderColor(), а потом нарисуй его.
//
//
//        Requirements:
//        1. Класс RedShapeDecorator должен быть потомком класса ShapeDecorator.
//        2. Метод setBorderColor() должен выводить на экран фразу соответствующую условию задачи.
//        3. Метод setBorderColor() должен быть приватным.
//        4. В методе draw() класса RedShapeDecorator должен быть изменен цвет декорируемого объекта и вызван его метод draw().
//        5. Публичный

public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    private void setBorderColor(Shape shape)
    {
        System.out.println("Setting the border color for " + shape.getClass().getSimpleName() + " to red.");
    }

    @Override
    public void draw() {
        setBorderColor(this.decoratedShape);
        super.draw();
    }
}
