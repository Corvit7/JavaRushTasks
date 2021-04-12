package com.javarush.task.task37.task3707;

import java.io.*;
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

    @Override
    public Object clone() throws CloneNotSupportedException {
        AmigoSet clon = (AmigoSet)super.clone();
        try {
            clon.map = (HashMap)map.clone();
        } catch (Exception e)
        {
            throw new InternalError();
        }
        return clon;
    }

    private void readObject(ObjectInputStream inputStream)
    {
        try {
            inputStream.defaultReadObject();
            float loadFactor = (float)inputStream.readObject();
            int capacity = (int)inputStream.readObject();
            map = new HashMap<>(capacity, loadFactor);
            int size = (int)inputStream.readObject();
            for (int i = 0; i < size; i++) {
                E e = (E)inputStream.readObject();
                map.put(e, PRESENT);
            }
        } catch (ClassNotFoundException | IOException e)
        {
            e.printStackTrace();
        }
    }

    private void writeObject(ObjectOutputStream outputStream)
    {
        try {
            outputStream.defaultWriteObject();
            outputStream.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
            outputStream.writeObject(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
            outputStream.writeObject(map.size());
            for (E e: map.keySet()
                 ) {
                outputStream.writeObject(e);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Collection<Long> src = new HashSet<>();
        src.add(1L);
        src.add(2L);
        src.add(3L);
        AmigoSet dst = new AmigoSet<>(src);
//        dst.remove(3);
//        for (Integer i: dst
//             ) {
//            System.out.println(i);
//        }
//        try {
//            Object clon = dst.clone();
//            for (Integer i: (AmigoSet<Integer>)clon
//            ) {
//                System.out.println(i);
//            }
//        } catch (CloneNotSupportedException e)
//        {
//            e.printStackTrace();
//        }

        try {
            FileOutputStream outputStream = new FileOutputStream("C:\\repos\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task37\\save.ser");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(dst);

            FileInputStream fileInputStream = new FileInputStream("C:\\repos\\JavaRushTasks\\4.JavaCollections\\src\\com\\javarush\\task\\task37\\save.ser");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            AmigoSet deserialized = (AmigoSet)objectInputStream.readObject();
            System.out.println("Finish");
        } catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

}
