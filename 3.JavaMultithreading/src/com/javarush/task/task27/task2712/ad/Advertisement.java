package com.javarush.task.task27.task2712.ad;

public class Advertisement {
    /*
    Будем считать, что у нас известно количество оплаченных показов,
    общая стоимость всех показов и сам рекламный ролик
    */

    private Object content;                 //видеоролик
    private String name;                    //имя
    private long initialAmount;             //оплаченная сумма в копейках
    private int hits;                       //оплаченное количество показов
    private int duration;                   //хронометраж ролика в секунда
    private long amountPerOneDisplaying;    //стоимость показа одного рекламного ролика в копейках

    public Advertisement(Object content, String name, long initialAmount, int hits, int duration) {
        this.content = content;
        this.name = name;
        this.initialAmount = initialAmount;
        this.hits = hits;
        this.duration = duration;
        this.amountPerOneDisplaying = (hits > 0) ? initialAmount / (long) hits : 0;
    }

    public void revalidate() throws UnsupportedOperationException{
        if (hits <= 0) throw new UnsupportedOperationException();
        else hits--;
    }

    public String getName() {
        return name;
    }

    public long getAmountPerOneDisplaying() {
        return amountPerOneDisplaying;
    }

    public long getAmountPerOneSeconds() {
        return amountPerOneDisplaying * 1000 / duration;
    }

    public int getHits() {
        return hits;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return name + " is displaying... " + amountPerOneDisplaying + ", " + this.getAmountPerOneSeconds();

    }
}
