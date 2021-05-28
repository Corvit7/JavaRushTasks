package com.javarush.task.task38.task3803;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/* 
Runtime исключения (unchecked exception)
*/

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object x = new Integer(0);
        System.out.println((String)x);
    }

    public void methodThrowsNullPointerException() {
        new TestNPE().handle(null, "test");
    }

     class Formatter {
        public String format(String value) {
            return "["+value+"]";
            }
     }

    public class TestNPE {
    public  String handle(Formatter f, String s) {
            if(s.isEmpty()) {
                return "(none)";
                }
            return f.format(s.trim());
            }
    }

    public static void main(String[] args) {
        new VeryComplexClass().methodThrowsNullPointerException();
    }
}
