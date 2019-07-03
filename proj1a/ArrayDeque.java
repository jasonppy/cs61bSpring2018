public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private double usageFactor = 0.25;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int current = plusOne(nextFirst);
        while(current != nextLast) {
            System.out.println(items[current]);
            current = plusOne(current);
        }
    }

    public void resize(int cap) {
        T[] a = (T[]) new Object[cap];
        int current = plusOne(nextFirst);
        for (int i = 0; i < size - 1; i++) {
            a[i] = items[current];
            current = plusOne(current);
        }
        items = a;
    }

    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    public int plusOne(int index) {
        index++;
        if(index == items.length) {
            index = 0;
        }
        return index;
    }

    public int minusOne(int index) {
        index--;
        if(index < 0) {
            index = items.length - 1;
        }
        return index;
    }

    public void addFirst(T x) {
        if(size == items.length - 1) {
            resize(size * 2);
        }
        size++;
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
    }

    public void addLast(T x) {
        if(size == items.length - 1) {
            resize(size * 2);
        }
        size++;
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
    }

    public T removeFirst() {
        if(size == 0) {
            return null;
        }
        size--;
        int first = plusOne(nextFirst);
        T item = items[first];
        items[first] = null;
        nextFirst = first;
        if(items.length >= 16 && size/items.length < usageFactor) {
            resize(Math.toIntExact(Math.round(size * usageFactor)));
        }
        return item;
    }

    public T removeLast() {
        if(size == 0) {
            return null;
        }
        size--;
        int last = minusOne(nextLast);
        T item = items[last];
        items[last] = null;
        if(items.length >= 16 && size/items.length < usageFactor) {
            resize(Math.toIntExact(Math.round(size * usageFactor)));
        }
        return item;
    }

    public T get(int index) {
        if(index > size - 1) {
            return null;
        }
        int current = plusOne(nextFirst);
        while(index > 0) {
            current = plusOne(current);
            index --;
        }
        return items[current];
    }

}