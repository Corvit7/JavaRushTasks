package com.javarush.task.task34.task3412;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/* 
Добавление логирования в класс
*/

public class Solution {
    private static final Logger logger = LoggerFactory.getLogger(Solution.class);

    private int value1;
    private String value2;
    private Date value3;

    public Solution(int value1, String value2, Date value3) {
        logger.debug ("Add value of construct {} {} {}",value1,value2,value3);
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
    }

    public static void main(String[] args) {

        Solution solution = new Solution(12, "test", new Date());
        solution.calculateAndSetValue3(15L);
        solution.printString();
        solution.printDateAsLong();
        solution.divide(1,0);
        solution.setValue1(7);
        solution.setValue2("change");
        solution.setValue3(new Date());
    }

    public void calculateAndSetValue3(long value) {
        logger.trace("Starting  calculateAndSetValue3()");
        value -= 133;
        if (value > Integer.MAX_VALUE) {
            //logger.debug("value1 change. Prev value: {}", value1);
            value1 = (int) (value / Integer.MAX_VALUE);
            logger.debug("value1 change. New value: {}", (int) (value / Integer.MAX_VALUE));
        } else {
//            logger.debug("value1 change. Prev value: {} New value: {}", value1, (int) value);
            value1 = (int) value;
            logger.debug("value1 change. New value: {}", (int) value);
        }
    }

    public void printString() {
        if (value2 != null) {
            logger.trace("value2.length(): {}", value2.length());
            System.out.println(value2.length());
        }
    }

    public void printDateAsLong() {
        if (value3 != null) {
            logger.trace("value3.getTime(): {}", value3.getTime());
            System.out.println(value3.getTime());
        }
    }

    public void divide(int number1, int number2) {

        try {
            logger.trace("number1 / number2: {}", number1 / number2);
            System.out.println(number1 / number2);
        } catch (ArithmeticException e) {
            for (StackTraceElement el: e.getStackTrace()
                 ) {
                logger.error(el.toString());
            }
        }
    }

    public void setValue1(int value1) {
//        logger.debug("value1 change. Prev value: {} New value: {}", this.value1, value1);
        this.value1 = value1;
        logger.debug("value1 change. New value: {}", value1);
    }

    public void setValue2(String value2)
    {
//        logger.debug("value2 change. Prev value: {} New value: {}", this.value2, value2);
        this.value2 = value2;
        logger.debug("value2 change. New value: {}", value2);
    }

    public void setValue3(Date value3) {
//        logger.debug("value3 change. Prev value: {} New value: {}", this.value3, value3);
        this.value3 = value3;
        logger.debug("value3 change. New value: {}", value3);
    }
}
