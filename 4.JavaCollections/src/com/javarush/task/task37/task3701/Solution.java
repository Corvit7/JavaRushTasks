package com.javarush.task.task37.task3701;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.function.Consumer;

/* 
Круговой итератор
*/

public class Solution<T> extends ArrayList<T> {
    public static void main(String[] args) {
        Solution<Integer> list = new Solution<>();
        list.add(1);
        list.add(2);
        list.add(3);

        int count = 0;
        for (Integer i : list) {
            //1 2 3 1 2 3 1 2 3 1
            System.out.print(i + " ");
            count++;
            if (count == 10) {
                break;
            }
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new RoundIterator();
    }

    public class RoundIterator implements Iterator<T> {
        private Iterator<T> iterator = Solution.super.iterator();
        @Override
        public void remove() {
            iterator.remove();
//            Solution.super.iterator().remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super T> action) {
            iterator.forEachRemaining(action);
        }

        @Override
        public boolean hasNext() {
            if (iterator.hasNext()) {
            }
            else {
                iterator = Solution.super.iterator();
            }
            return iterator.hasNext();
        }

        @Override
        public T next() {
            return iterator.next();
        }
    }
}
