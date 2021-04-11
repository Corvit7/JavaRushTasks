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
        Collection<E> keySet = map.keySet();
        return keySet.iterator();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean remove(Object o) {
        return null==map.remove(o);
    }

    public static void main(String[] args) {
        Collection<Integer> src = new HashSet<>();
        src.add(1);
        src.add(2);
        src.add(3);
        AmigoSet<Integer> dst = new AmigoSet<>(src);
        dst.remove(3);
        for (Integer i: dst
             ) {
            System.out.println(i);
        }
    }

}
