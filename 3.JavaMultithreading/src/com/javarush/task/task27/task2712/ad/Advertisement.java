package com.javarush.task.task27.task2712.ad;

public class Advertisement {
    private Object content;
    private String name;
//    long initialAmount - начальная сумма, стоимость рекламы в копейках. Используем long, чтобы избежать проблем с округлением
    private long initialAmount;
//    int hits - количество оплаченных показов
    private int hits;
//    int duration - продолжительность в секундах
    private int duration;

    private long amountPerOneDisplaying;

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        this.amountPerOneDisplaying = initialAmount/hits;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public long getInitialAmount() {
        return initialAmount;
    }

    public void revalidate() throws UnsupportedOperationException {
        if(hits==0)
            throw new UnsupportedOperationException();
        else
            hits--;
    }

    @Override
    public String toString() {
        return name + " is displaying..." + initialAmount + ", " + initialAmount/duration;
    }
}
