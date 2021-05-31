package com.javarush.task.task37.task3711;

public class Computer {
    CPU cpu;
    HardDrive hardDrive;
    Memory memory;

    public Computer() {
        cpu = new CPU();
        hardDrive = new HardDrive();
        memory = new Memory();
    }

    public void run(){
        cpu.calculate();
        memory.allocate();
        hardDrive.storeData();
    }
}
