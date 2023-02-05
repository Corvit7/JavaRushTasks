package com.javarush.task.task35.task3507;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/

//public class Solution {
//    public static void main(String[] args) {
//        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
//        System.out.println(allAnimals);
//    }
//
//    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
//        Set<Animal> animals = new HashSet<>();
//        return null;
//    }
//}

public class Solution {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, NoSuchFieldException {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain()
                .getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName()
                .replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, NoSuchFieldException {
        Set<Animal> set = new HashSet<>();
        File file = new File(pathToAnimals);
        File[] fileList = file.listFiles();
        if (fileList != null && fileList.length > 0) {
            for (File f: fileList) {
                if (f.getName().endsWith(".class")) {
                    String className = f.getName().replace(".class", "");
                    Class clazz = Class.forName((pathToAnimals.substring(pathToAnimals.indexOf("com")) + "/" + className)
                            .replace("/", "."));
                    Class[] interfaces = clazz.getInterfaces();
                    boolean isAnimal = false;
                    for (Class c: interfaces) {
                        if (c.equals(Animal.class)) { isAnimal = true;}
                    }

                    for (Constructor c: clazz.getDeclaredConstructors()) {
                        if (isAnimal && c.toString().startsWith("public") && c.getParameterCount() == 0) {
                            Animal animal = (Animal) clazz.newInstance();
                            set.add(animal);
                        }
                    }
                }
            }
        }
        return set;
    }
}