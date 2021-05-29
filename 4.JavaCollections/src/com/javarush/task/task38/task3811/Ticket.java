package com.javarush.task.task38.task3811;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Target(value = {ElementType.CONSTRUCTOR, ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
//public @interface Ticket {
//    Priority priority() default Priority.MEDIUM;
//    String[] tags();
//    String createdBy() default "Amigo";
//
//    public enum Priority {
//        LOW,
//        MEDIUM,
//        HIGH
//    }
//
//}


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Ticket {
    enum Priority {
        LOW, MEDIUM, HIGH
    }

    Priority priority() default Priority.MEDIUM;
    String[] tags() default {};
    String createdBy() default "Amigo";
}
