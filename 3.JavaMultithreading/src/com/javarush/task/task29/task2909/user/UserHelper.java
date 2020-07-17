package com.javarush.task.task29.task2909.user;

import java.util.concurrent.atomic.AtomicInteger;

public class UserHelper {
    private User userAnya = new User("Аня", "Смирнова", 10);
    private User userRoma = new User("Рома", "Виноградов", 30);

//    private boolean isManAnya = false;
//    private boolean isManRoma = true;



    public void printUsers() {

        userAnya.printInfo();
        userAnya.printAdditionalInfo();
//        printAdditionalInfo(userAnya);

        userRoma.printInfo();
        userRoma.printAdditionalInfo();
//        printAdditionalInfo(userRoma);
    }



//    private boolean ageLessThan16(User user) {
//        if (user.getAge() < 16) {
//            return true;
//        }
//        return false;
//    }

    public int calculateAverageAge() {
//        int age = 28;
        User userUra = new User("Юра", "Карп", 28);

        return (userAnya.getAge() + userRoma.getAge() + userUra.getAge()) / 3;

//        return age;
    }

    public int calculateRate(AtomicInteger base, int age, boolean hasWork, boolean hasHouse) {
//        base.set(base.get() + age / 100);
//        base.set((int) (base.get() * (hasWork ? 1.1 : 0.9)));
//        base.set((int) (base.get() * (hasHouse ? 1.1 : 0.9)));
        int local = base.get() + age / 100;
        local = (int) (local * (hasWork ? 1.1 : 0.9));
        local = (int) (local * (hasHouse ? 1.1 : 0.9));
        return local;
    }

    public String getBossName(User user) {
//        Work work = user.getWork();
//        return work.getBoss();
        return user.getBoss();
    }
}