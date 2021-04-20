package com.javarush.task.task37.task3702;

import com.javarush.task.task37.task3702.female.FemaleFactory;
import com.javarush.task.task37.task3702.male.MaleFactory;

public class FactoryProducer {
    public static enum HumanFactoryType{
        MALE,
        FEMALE
    }

    public static AbstractFactory getFactory (HumanFactoryType type){
        AbstractFactory obj = null;
        switch (type){
            case MALE: obj = new MaleFactory();
            break;
            case FEMALE: obj = new FemaleFactory();
            break;
        }
        return obj;
    }
}
