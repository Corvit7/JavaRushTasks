package com.javarush.task.task20.task2028;

import java.io.Serializable;
import java.util.*;


public class CustomTree extends AbstractList<String> implements Cloneable, Serializable {
    

    @Override
    public String get(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String set(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int index, String element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(String s) {
        boolean elementAdded = false;
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        Entry<String> newE = new Entry<>(s);
        Entry<String> curE = null;
        do{
            if(!queue.isEmpty())
                curE = queue.poll();
            if(curE.isAvailableToAddChildren())
            {
                if (curE.availableToAddLeftChildren)
                {
                    newE.parent = curE;
                    elementAdded = true;
                    curE.availableToAddLeftChildren = false;
                    curE.leftChild = newE;
                } else if (curE.availableToAddRightChildren)
                {
                    newE.parent = curE;
                    elementAdded = true;
                    curE.availableToAddRightChildren = false;
                    curE.rightChild = newE;
                }
            } else
            {
                if(curE.leftChild != null)
                    queue.add(curE.leftChild);
                if(curE.rightChild != null)
                    queue.add(curE.rightChild);
                if(curE.leftChild == null && curE.rightChild == null)
                {
                    curE.availableToAddRightChildren = true;
                    curE.availableToAddLeftChildren = true;
                    queue.add(curE);
                }
            }

        } while (!queue.isEmpty() && !elementAdded);
        return true;
    }

    @Override
    public boolean remove(Object index) {
        if(!(index instanceof String))
            throw new UnsupportedOperationException();
        boolean elementRemoved = false;
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        Entry<String> curE = null;
        do {
            if(!queue.isEmpty())
                curE = queue.poll();
            if (curE.elementName.equals(index))
            {
                if(curE.parent.leftChild != null) {
                    if (curE.parent.leftChild.elementName.equals(curE.elementName)) {
                        curE.parent.leftChild = null;
                        //curE.parent.availableToAddLeftChildren = true;
                    }
                }
                if(curE.parent.rightChild != null) {
                    if (curE.parent.rightChild.elementName.equals(curE.elementName)) {
                        curE.parent.rightChild = null;
                        //curE.parent.availableToAddLeftChildren = true;
                    }
                }
                elementRemoved = true;
            } else
            {
                if (curE.leftChild != null)
                    queue.add(curE.leftChild);
                if (curE.rightChild != null)
                    queue.add(curE.rightChild);
            }
        } while (!queue.isEmpty() && !elementRemoved);
        return elementRemoved;
    }

    @Override
    public List<String> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends String> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        int result = 0;
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        Entry<String> curE = null;
        do {
            if(!queue.isEmpty()) curE = queue.poll();
            if (curE.rightChild != null)
            {
                queue.add(curE.rightChild);
                result++;
            }
            if(curE.leftChild != null)
            {
                queue.add(curE.leftChild);
                result++;
            }
        } while (!queue.isEmpty());
        return result;
    }


    public String getParent(String s)
    {
        boolean elementFound = false;
        Queue<Entry<String>> queue = new LinkedList<>();
        queue.add(root);
        Entry<String> curE = null;
        do {
            if(!queue.isEmpty()) curE = queue.poll();
            if (curE.elementName.equals(s))
            {
                elementFound = true;
            } else
            {
                if (curE.rightChild != null)
                    queue.add(curE.rightChild);
                if(curE.leftChild != null)
                    queue.add(curE.leftChild);
            }
        } while (!queue.isEmpty() && !elementFound);
        if(!elementFound)
            return "null";
        return curE.parent.elementName;
    }


    static class Entry<T> implements Serializable
    {
        String elementName;
        boolean availableToAddLeftChildren, availableToAddRightChildren;
        Entry<T> parent, leftChild, rightChild;

        public Entry(String string) {
            elementName = string;
            availableToAddLeftChildren = true;
            availableToAddRightChildren = true;
        }

        public boolean isAvailableToAddChildren() {
            return availableToAddRightChildren || availableToAddLeftChildren;
        }
    }

    Entry<String> root;

    public CustomTree() {
        this.root = new Entry<>("test");
    }
}
