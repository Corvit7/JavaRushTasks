package com.javarush.task.task37.task3707;

import java.io.Serializable;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Cloneable, Serializable, Set<E> {

    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet() {
        this.map = new HashMap<>();
    }

    @Override
    public boolean add(E e) {
        return null == map.put(e, PRESENT);
    }

//    public HashMap<E, Object> getMap() {
//        return map;
//    }

    public AmigoSet(Collection<? extends E> collection) {
        int capacity = Math.max(16, (int)Math.ceil(collection.size()/.75f));
        this.map = new HashMap<>(capacity);
        this.addAll(collection);

    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    public static void main(String[] args) {
        Collection<Integer> src = new HashSet<>();
        src.add(1);
        src.add(2);
        src.add(3);
        AmigoSet<Integer> dst = new AmigoSet<>(src);
//        HashMap test = dst.getMap();
//        AmigoSet test = new
    }

}
