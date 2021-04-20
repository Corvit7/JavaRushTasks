package com.javarush.task.task37.task3702.male;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

public class MaleFactory implements Human, AbstractFactory {
    public Human getPerson(int age) {
        if( age >= 0 && age < KidBoy.MAX_AGE)
            return new KidBoy();
        else if( age > 12 && age < 20)
            return new TeenBoy();
        else
            return new Man();
    }
}
