public class ArrayDeque<T> implements Deque<T>{
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
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        int current = plusOne(nextFirst);
        while (current != nextLast) {
            System.out.println(items[current]);
            current = plusOne(current);
        }
    }

    private void resize(int cap) {
        T[] a = (T[]) new Object[cap];
        int current = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            a[i] = items[current];
            current = plusOne(current);
        }
        nextFirst = a.length - 1;
        nextLast = size;
        items = a;
    }
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    private int plusOne(int index) {
        index++;
        if (index == items.length) {
            index = 0;
        }
        return index;
    }

    private int minusOne(int index) {
        index--;
        if (index < 0) {
            index = items.length - 1;
        }
        return index;
    }
    @Override
    public void addFirst(T x) {
        if (size == items.length - 1) {
            resize(size * 2);
        }
        size++;
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
    }
    @Override
    public void addLast(T x) {
        if (size == items.length - 1) {
            resize(size * 2);
        }
        size++;
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
    }
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        nextFirst = plusOne(nextFirst);
        T item = items[nextFirst];
        items[nextFirst] = null;
        double actualFactor = size * 1.0 / items.length;
        if (items.length >= 16 && actualFactor < usageFactor) {
            resize(Math.toIntExact(Math.round(items.length * usageFactor + 1.0)));
        }
        return item;
    }
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        nextLast = minusOne(nextLast);
        T item = items[nextLast];
        items[nextLast] = null;
        double actualFactor = size * 1.0 / items.length;
        if (items.length >= 16 && actualFactor < usageFactor) {
            resize(Math.toIntExact(Math.round(items.length * usageFactor + 1.0)));
        }
        return item;
    }
    @Override
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }
        int current = plusOne(nextFirst);
        while (index > 0) {
            current = plusOne(current);
            index--;
        }
        return items[current];
    }

}
